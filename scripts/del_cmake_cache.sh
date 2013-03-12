
echo "rm cmake cache begin..."

cd ../
make clean
find . -type d -name "CMakeFiles"|xargs rm -rf
find . -type f -name "cmake_install.cmake"|xargs rm
find . -type f -name "CMakeCache.txt"|xargs rm
find . -type f -name "Makefile"|xargs rm

echo "rm cmake cache end..."