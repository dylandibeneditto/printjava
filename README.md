
# PrintJava

![Render of a Mandelbulb using PrintJava](./.github/mandelbulb.png)
> The Mandelbulb Fractal created using PrintJava

This library allows for communication with 3d print files and java. Meshes are converted to `.stl` files, which can be provided for prints.

## JGrasp Setup

1. install `printjava.jar`
2. move the jar file to any directory
3. copy the path to the jar file (`path\to\jar\printjava.jar`)
4. go to `Settings > PATH/CLASSPATH > Workspace`
5. press the `CLASSPATHS` tab
6. press `New`
7. paste the path to the jar file
8. press `Apply` then `OK`
9. restart your JGrasp

Now this library will be accessable throughout all of JGrasp through the use of the `import` keyword.

### Manually Compiling

You can manually compile this library, just run the following code:

```bash
javac ./printjava/printjava/*.java ./printjava/printjava/Meshes/*.java; jar cvf printjava.jar -C printjava .
```
