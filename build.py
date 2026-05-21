import os

VERSION = "1.13"
JAR_NAME = f"printjava-{VERSION}.jar"
SRC_JAR_NAME = f"printjava-{VERSION}-sources.jar"

print(f"Building printjava version {VERSION}...")

# 1. Compile the code into 'out'
if not os.path.exists("out"):
    os.makedirs("out")

print("Compiling...")
os.system("javac -d out printjava\\*.java printjava\\Meshes\\*.java")

# 2. Create the Library JAR (Compiled .class files)
print(f"Creating Library JAR: {JAR_NAME}")
os.system(f"jar cf {JAR_NAME} -C out .")

# 3. Create the Sources JAR (The .java files with your docstrings)
print(f"Creating Sources JAR: {SRC_JAR_NAME}")
# This packs the original java files so IDEs can show the comments
os.system(f"jar cf {SRC_JAR_NAME} printjava\\*.java printjava\\Meshes\\*.java")

# 4. Cleanup
print("Cleaning up 'out' folder...")
os.system("rmdir out /s /q")

print("Done! You now have the library and the source bundle.")