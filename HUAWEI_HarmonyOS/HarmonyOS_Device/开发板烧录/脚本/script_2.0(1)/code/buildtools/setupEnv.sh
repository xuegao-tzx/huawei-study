#!/usr/bin/env bash
good="\033[32m"
bad="\033[31m"
reset="! \033[0m"

cd $(dirname $0)

for app in rsync patch wget nano make pv zip
do
    which $app >/dev/null || apt install -y $app
done

function checkNinja() {
    ret=`ninja --version`
    if [[ ! $ret =~ 1\.9 ]]; then
        wget -c https://repo.huaweicloud.com/harmonyos/compiler/ninja/1.9.0/linux/ninja.1.9.0.tar
        tar xpf ninja.1.9.0.tar
        echo -e "$good √ Ninja: 1.9.0 install!$reset"
        rm ninja.1.9.0.tar
    else
        echo -e "$good √ Ninja: $ret checked$reset"
    fi
}

function checkGN() {
    ret=`gn --version`
    if [[ ! $ret =~ 1[5-9][0-9]{2} ]]; then
        wget -c https://repo.huaweicloud.com/harmonyos/compiler/gn/1523/linux/gn.1523.tar
        tar xpf gn.1523.tar
        echo -e "$good √ GN: 1523 install!$reset"
        rm gn.1523.tar
    else
        echo -e "$good √ GN version $ret checked$reset"
    fi
}

function checkGCC() {
    ret=`riscv32-unknown-elf-gcc -v 2>&1 | egrep -E -o " 7\.[0-9]{1,2}\.[0-9]{1,2}"`
    if [[ ! $ret =~ 7\.[3-9] ]]; then
        wget -c https://repo.huaweicloud.com/harmonyos/compiler/gcc_riscv32/7.3.0/linux/gcc_riscv32-linux-7.3.0.tar.gz
        pv gcc_riscv32-linux-7.3.0.tar.gz | tar xpf -
        echo -e "$good √ Riscv_GCC: 7.3.0 install!$reset"
        rm gcc_riscv32-linux-7.3.0.tar.gz
    else
        echo -e "$good √ Riscv_GCC version $ret checked $reset"
    fi
}

function installCode() {
    cd ..
    if [ ! -f build.py ]; then
        if [ -f code-1.0.tar.gz ]; then 
            echo -e "$good √ Code downloaded！$reset"
        else
            wget -c https://repo.huaweicloud.com/harmonyos/os/1.0/code-1.0.tar.gz
        fi
        pv code-1.0.tar.gz | tar xzpf -
        echo -e "$good √ Code: 1.0 install $reset"
        rm code-1.0.tar.gz
    else 
        echo -e "$good √ Code: 1.0 checked $reset"
    fi
}

checkGN
checkNinja
checkGCC
installCode
