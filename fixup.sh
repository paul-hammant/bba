#!/bin/sh

# watch for first few lines of file - includes

sed -i '' -E 's/([[:digit:]]+\.[[:digit:]]{1})[[:digit:]]*/\1/g' index.html
sed -i '' -E 's/&quot;#markerArrowBlock&quot;/#markerArrowBlock/g' index.html