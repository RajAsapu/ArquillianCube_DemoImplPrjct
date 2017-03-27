#!/usr/bin/env bash

repos[0]="price-configuration-service.git"
repos[1]="price-data-mock.git"
repos[2]="price-engine.git"
repos[3]="price-configuration-ui.git"

for i in {0..2}
do
    path=$(dirname $0)/pricing/Repos/${repos[$i]}
    path=${path/"/pricing/src/test/java/dockerhandler"/""}
    if [[ -e $path ]]
    then
        echo "<--${repos[$i]}-->"
        echo "Building maven project..."
        eval $"cd $path"
        eval $'mvn -C -q package' >> out.txt
        eval $"cp $path/target/*.jar  $path"
        echo "<--Completed-->"
    fi
done

priceui=$(dirname $0)/pricing/Repos/${repos[3]}
priceui=${priceui/"/pricing/src/test/java/dockerhandler"/""}

if [[ -e $priceui ]]
then
    echo "<--Price Ui-->"
    echo "Building Angular project..."
    eval $"cd $priceui"
#   eval $'npm install --save-dev @angular/compiler-cli@2.3.1' >> out.txt
    eval $'ng build' >> out.txt
    echo "<--Completed-->"
fi