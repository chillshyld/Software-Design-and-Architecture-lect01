package Pattern;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Chieh on 1/12/2017 AD.
 */
public class PieChartController extends JPanel implements Observer{

    public PieChartController(CourseData data) {
        data.attach(this);
        this.courseData = data.getUpdate();
        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset ));
        this.setBackground(Color.white);
    }

    public void paint(Graphics g) {
        super.paint(g);

        int radius = 100;

        double total = 0.0;
        for (int i = 0;i < courseData.size();i++)
            total += courseData.get(i).getNumOfStudents();

        if (total != 0) {
            double startAngle = 0.0;
            for (int i = 0; i < courseData.size(); i++) {
                double ratio = (courseData.get(i).getNumOfStudents() / total) * 360.0;
                //draw the arc
                g.setColor(LayoutConstants.courseColours[i% LayoutConstants.courseColours.length]);
                g.fillArc(LayoutConstants.xOffset, LayoutConstants.yOffset , 2 * radius, 2 * radius, (int) startAngle, (int) ratio);
                startAngle += ratio;
            }
        }


    }

    public void update(Object o) {
        this.courseData = (ArrayList<CourseRecord>) o;

        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.revalidate();
        this.repaint();
    }

    private ArrayList<CourseRecord> courseData;

}
