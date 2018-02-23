#!/bin/bash

POM_VER=$1

function usage(){
    
    echo 

    echo usage : updatePomVersions.sh [version]

    exit
}

if [ -z ${POM_VER} ]; then
    echo
    echo $0 : Missing version
    usage
fi

echo updating pom versions to ${POM_VER}

read -p "Press enter to continue..."

mvn versions:set -DnewVersion=${POM_VER}

