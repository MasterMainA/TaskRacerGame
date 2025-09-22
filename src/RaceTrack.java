import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
class RaceTrack {
    private GeneralPath outerTrackPath; // Внешние границы
    private GeneralPath innerTrackPath;  // Внутренние границы (бордюры)
    private Area roadArea;               // Область дороги

    public RaceTrack() {
        this.outerTrackPath = createOuterTrack();
        this.innerTrackPath = createInnerTrack();
        this.roadArea = createRoadArea();
    }

    private Area createRoadArea() {
        Area area = new Area(outerTrackPath);
        area.subtract(new Area(innerTrackPath));
        return area;
    }

    public void draw(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;

        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 1100, 700);
        g.fillRect(50, 50, 1100, 700);

        Color color = new Color(79, 144, 24);
        g.setColor(color);
        g.drawRect(150, 150, 900, 500);
        g.fillRect(150, 150, 900, 500);

        g.setColor(Color.GRAY);
        g.fill(roadArea);

        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(3f));
        g.draw(outerTrackPath);
        g.draw(innerTrackPath);

//        // Центральная разделительная линия
//        g.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT,
//                BasicStroke.JOIN_BEVEL, 0, new float[]{10, 10}, 0));
//        g.setColor(Color.YELLOW);
//        // drawCenterLine(g);
    }

    private GeneralPath createOuterTrack() {
        GeneralPath trackPath = new GeneralPath();

        trackPath.moveTo(230, 625);
        trackPath.lineTo(1000, 625);

        trackPath.curveTo(1010, 625,
                1025, 625,
                1025, 600);

        trackPath.lineTo(1025, 225);

        trackPath.curveTo(1025, 215,
                1025, 200,
                1000, 200);

        trackPath.lineTo(550, 200);

        trackPath.curveTo(550, 200,
                475, 125,
                400, 200);

        trackPath.lineTo(230, 200);

        trackPath.curveTo(230, 200,
                175, 210,
                175, 262.5);

        trackPath.curveTo(175, 262.5,
                175, 315,
                230, 325);

        trackPath.lineTo(700, 325);

        trackPath.curveTo(700, 325,
                715, 337.5,
                700, 350);

        trackPath.lineTo(230, 350);

        trackPath.curveTo(230, 350,
                175, 360,
                175, 412.5);

        trackPath.curveTo(175, 412.5,
                175, 465,
                230, 475);

        trackPath.lineTo(700, 475);

        trackPath.curveTo(700, 325 + 150,
                715, 337.5+150,
                700, 350+150);

        trackPath.lineTo(230, 350+150);

        trackPath.curveTo(230, 350+150,
                175, 360+150,
                175, 412.5+150);

        trackPath.curveTo(175, 412.5+150,
                175, 465+150,
                230, 475+150);

        return trackPath;
    }

    private GeneralPath createInnerTrack() {
        GeneralPath trackPath = new GeneralPath();

        trackPath.moveTo(250, 575);
        trackPath.lineTo(950, 575);

        trackPath.curveTo(960, 575,
                975, 575,
                975, 550);

        trackPath.lineTo(975, 275);

        trackPath.curveTo(975, 265,
                975, 250,
                950, 250);

        trackPath.lineTo(550, 250);

        trackPath.curveTo(550, 250,
                475, 175,
                400, 250);

        trackPath.lineTo(250, 250);

        trackPath.curveTo(250, 250,
                235, 262.5,
                250, 275);

        trackPath.lineTo(720, 275);

        trackPath.curveTo(720, 275,
                765, 285,
                765, 337.5);

        trackPath.curveTo(765, 337.5,
                765, 390,
                720, 400);

        trackPath.lineTo(250, 400);

        trackPath.curveTo(250, 400,
                235, 412.5,
                250, 425);

        trackPath.lineTo(720, 425);

        trackPath.curveTo(720, 425,
                765, 435,
                765, 487.5);

        trackPath.curveTo(765, 487.5,
                765, 540.0,
                720, 550.0);

        trackPath.lineTo(250, 550.0);

        trackPath.curveTo(250, 400+150,
                235, 412.5+150,
                250, 425+150);

        trackPath.closePath();

        return trackPath;
    }
}