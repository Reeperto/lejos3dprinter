package ev3dprinter;

public class GCode {

    public static String[] parseGCode(String gcode) {
        String[] gcodeLines = gcode.split("\n");
        return gcodeLines;
    }

}
