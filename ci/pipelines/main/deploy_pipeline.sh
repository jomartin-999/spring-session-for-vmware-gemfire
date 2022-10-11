#!/usr/bin/env bash


#
# Copyright (c) VMware, Inc. 2022. All rights reserved.
# SPDX-License-Identifier: Apache-2.0
#

SOURCE="${BASH_SOURCE[0]}"
SCRIPT_DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"

for cmd in Jinja2 PyYAML; do
  if ! [[ $(pip3 list |grep ${cmd}) ]]; then
    echo "${cmd} must be installed for pipeline deployment to work."
    echo " 'pip3 install ${cmd}'"
    echo ""
    exit 1
  fi
done

META_PROPERTIES=${SCRIPT_DIR}/meta.properties
LOCAL_META_PROPERTIES=${SCRIPT_DIR}/meta.properties.local

## Load default properties
source ${META_PROPERTIES}
echo "**************************************************"
echo "Default Environment variables for this deployment:"
cat ${SCRIPT_DIR}/meta.properties | grep -v "^#"
source ${META_PROPERTIES}

## Load local overrides properties file
if [[ -f ${LOCAL_META_PROPERTIES} ]]; then
  echo "Local Environment overrides for this deployment:"
  cat ${SCRIPT_DIR}/meta.properties.local
  source ${LOCAL_META_PROPERTIES}
  echo "**************************************************"
fi

read -n 1 -s -r -p "Press any key to continue or x to abort" DEPLOY
echo
if [[ "${DEPLOY}" == "x" ]]; then
  echo "x pressed, aborting deploy."
  exit 0
fi
set -e
set -x

FLY=$(command -v fly)
if [ -z "${FLY}" ]; then
  echo "Concourse 'fly' not found. Download the version-specific version at:"
  echo "  https://${CONCOURSE_HOST}"
  exit 1
fi

SSDG_FORK=$(yq -r .repositories.springSessionDataGemfire.fork \
  ${SCRIPT_DIR}/../shared/jinja.variables.yml)

SSDG_BRANCH=$(yq -r .repositories.springSessionDataGemfire.branch \
  ${SCRIPT_DIR}/../shared/jinja.variables.yml)

. ${SCRIPT_DIR}/../../scripts/utilities.sh
SANITIZED_SSDG_FORK=$(sanitizeName ${SSDG_FORK})
SANITIZED_SSDG_BRANCH=$(getSanitizedBranch ${SSDG_BRANCH})
PIPELINE_PREFIX="ssdg-${SANITIZED_SSDG_BRANCH}-"
PIPELINE_NAME="ssdg-${SANITIZED_SSDG_BRANCH}-main"
CONCOURSE_URL="https://${CONCOURSE_HOST}"
CONCOURSE_TEAM="${CONCOURSE_TEAM:-main}"


echo "FORK is ${SANITIZED_SSDG_FORK}"
echo "BRANCH is ${SANITIZED_SSDG_BRANCH}"

pushd ${SCRIPT_DIR} 2>&1 > /dev/null
  python3 ${SCRIPT_DIR}/../../scripts/render.py ${SCRIPT_DIR}/jinja.template.yml \
    --variable-file ${SCRIPT_DIR}/../shared/jinja.variables.yml \
    --environment ${SCRIPT_DIR}/../shared \
    --output ${SCRIPT_DIR}/generated-pipeline.yml || exit 1

  grep -n . ${SCRIPT_DIR}/generated-pipeline.yml

  TARGET=${CONCOURSE_HOST}-${CONCOURSE_TEAM}
  fly -t ${TARGET} status || \
    fly -t ${TARGET} login --concourse-url=${CONCOURSE_URL} \
                           --team-name=${CONCOURSE_TEAM}

  fly -t ${TARGET} set-pipeline \
    --pipeline ${PIPELINE_NAME} \
    --config ${SCRIPT_DIR}/generated-pipeline.yml \
    --var spring-session-data-gemfire-build-branch=${SANITIZED_SSDG_BRANCH} \
    --var pipeline-prefix=${PIPELINE_PREFIX} \
    --var gcp-project=${GCP_PROJECT} \
    --var concourse-team=${CONCOURSE_TEAM} \
    --var linux-base-family=ubuntu-minimal-2004-lts \
    --var semver-prerelease-token=${SEMVER_PRERELEASE_TOKEN} \
    --var artifact-bucket=${ARTIFACT_BUCKET} \
    --var concourse-url=${CONCOURSE_URL}

popd 2>&1 > /dev/null
