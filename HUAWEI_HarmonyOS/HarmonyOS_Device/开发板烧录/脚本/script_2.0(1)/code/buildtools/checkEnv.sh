#!/usr/bin/env bash
good="\033[32m"
bad="\033[31m"
reset="! \033[0m"

scons_result="$bad ● Need SCons v4 or higher$reset"
ninja_result="$bad ● Need Ninja v1.9 or higher$reset"
python_result=""
gn_result="$bad ● Need GN v1523 or higher$reset"
gcc_result="$bad ● Need GCC 7.3 or higher$reset"

function checkVersion() {
    ret=`scons -v | egrep -E -o "SCons: v([0-9]+)(\.([0-9])+){0,3}"`
    if [[ $ret =~ "SCons: v4." ]]; then
        scons_result="$good √ $ret checked$reset"
    fi

    ret=`ninja --version`
    if [[ $ret =~ 1\.9 ]]; then
        ninja_result="$good √ Ninja: $ret checked$reset"
    fi

    ret=`python --version`
    python_result="$bad ● $ret installed, need Python v3.8 or higher$reset"
    if [[ $ret =~ 3\.[8-9] ]]; then
        python_result="$good √ $ret checked$reset"
    fi

    ret=`gn --version`
    if [[ $ret =~ 1[5-9][0-9]{2} ]]; then
        gn_result="$good √ GN version $ret checked$reset"
    fi

    ret=`riscv32-unknown-elf-gcc -v 2>&1 | egrep -E -o " 7\.[0-9]{1,2}\.[0-9]{1,2}"`
    if [[ $ret =~ 7\.[3-9] ]]; then
        gcc_result="$good √ Riscv_GCC version $ret checked$reset"
    fi
}

function result() {
    echo -e $python_result
    echo -e $scons_result
    echo -e $ninja_result
    echo -e $gn_result
    echo -e $gcc_result
}

checkVersion
result