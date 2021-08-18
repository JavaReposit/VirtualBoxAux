#!/bin/bash

echo "Iniciando máquina " $1 " en modo " $2
vboxmanage startvm $1 --type=$2
