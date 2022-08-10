#!/usr/bin/env bash

#if anything errors, bail.
set -e

ssdgPath="spring-session-data-geode"

Clone() {
  # Clone SDG repo
  rm -rf "$ssdgPath"
  git clone https://github.com/spring-projects/spring-session-data-geode.git $ssdgPath
}

Checkout() {
  # Checkout correct branch
  cd "$ssdgPath" || exit
  git checkout $branch
}

CopyTestResources() {
  rm -rf $projectDir"/spring-session-data-gemfire-main/src/ssdg*"
  mkdir -p $projectDir"/src"
  cp -R $ssdgPath"/spring-session-data-geode/src/test/" $projectDir"/spring-session-data-gemfire/src/ssdg-test-read-only"
  cp -R $ssdgPath"/spring-session-data-geode/src/integration-test/" $projectDir"/spring-session-data-gemfire/src/ssdg-integration-test-read-only"
  cp -R $ssdgPath"/spring-session-data-geode/src/test/resources/org" $projectDir"/spring-session-data-gemfire/src/ssdg-integration-test-read-only/resources/"
#  cp -R $ssdgPath"/samples" $projectDir
}

while [[ $# -gt 0 ]]; do
  case $1 in
  -l)
    ssdgPath="$2"
    shift # past argument
    shift # past value
    ;;
  -b)
    branch="$2"
    shift # past argument
    shift # past value
    ;;
  -t)
    projectDir="$2"
    shift # past argument
    shift # past value
    ;;
  -h)
    Help
    exit
    ;;
  -* | --*)
    echo "Unknown option $1"
    exit 1
    ;;
  esac
done

Clone
Checkout
CopyTestResources