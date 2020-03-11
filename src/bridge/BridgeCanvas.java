/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

import framework.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;

/**
 *
 * @author Zach
 */
public class BridgeCanvas extends Canvas
{
    public BridgeCanvas(BridgeState state)
    {
        super(state);
        
        background = new RoundRectangle2D.Double(0, 0, 500, 250, 10, 10);
        
        ground = new RoundRectangle2D.Double(0, 125, 500, 125, 10, 10);
        
        river = new Rectangle2D.Double(200, 125, 100, 125);
        
        bridge = new GeneralPath();
        bridge.moveTo(175, 125);
        bridge.lineTo(200, 100);
        bridge.lineTo(300, 100);
        bridge.lineTo(325, 125);
        bridge.lineTo(300, 125);
        bridge.lineTo(300, 115);
        bridge.lineTo(200, 115);
        bridge.lineTo(200, 125);
        bridge.closePath();
        
        actorSetup();
        
        setPreferredSize(new Dimension(500, 250));
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        actorSetup();
        BridgeState s = (BridgeState) getCurrState();
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(new Color(25, 25, 112));
        g2.fill(background);
        
        g2.setColor(new Color(0, 100, 0));
        g2.fill(ground);
        
        g2.setColor(Color.BLUE);
        g2.fill(river);
        
        g2.setColor(new Color(139, 69, 19));
        g2.fill(bridge);
        
        p1.draw(g2);
        p2.draw(g2);
        p5.draw(g2);
        p10.draw(g2);
        
        g2.setFont(new Font("Sans Serif", Font.BOLD, 16));
        g2.drawString("Time: " + Integer.toString(s.getTimeSoFar()) + " minutes", 5, 239);
        
        g2.setColor(new Color(238, 232, 170, 128));
        g2.fill(light);
        
        g2.setColor(Color.gray);
        g2.fill(flashlight);
    }
    
     public static void main(String[] args)
     {
        JFrame frame = new JFrame("WaterJugCanvas Test");
        frame.setLayout(new GridLayout());
        frame.add(new BridgeCanvas(new BridgeState(Position.WEST,
                Position.WEST, Position.WEST, Position.WEST, Position.WEST, 0)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
     }
    private RoundRectangle2D background;
    private RoundRectangle2D ground;
    private Rectangle2D river;
    private GeneralPath bridge;
    private StickFigure p1;
    private StickFigure p2;
    private StickFigure p5;
    private StickFigure p10;
    private GeneralPath flashlight;
    private Arc2D light;
    
    private void actorSetup()
    {
        BridgeState s = (BridgeState) getCurrState();
        
        p1 = new StickFigure("P1");
        if (s.getP1Position().equals(Position.WEST))
        {
            p1.setLocation(new Point(10, 75));
        }
        else
        {
            p1.setLocation(new Point(470, 75));
        }
        p2 = new StickFigure("P2");
        if (s.getP2Position().equals(Position.WEST))
        {
            p2.setLocation(new Point(40, 75));
        }
        else
        {
            p2.setLocation(new Point(440, 75));
        }
        p5 = new StickFigure("P5");
        if (s.getP5Position().equals(Position.WEST))
        {
            p5.setLocation(new Point(70, 75));
        }
        else
        {
            p5.setLocation(new Point(410, 75));
        }
        p10 = new StickFigure("P10");
        if (s.getP10Position().equals(Position.WEST))
        {
            p10.setLocation(new Point(100, 75));
        }
        else
        {
            p10.setLocation(new Point(380, 75));
        }
        flashlight = new GeneralPath();
        if (s.getFlashlightPosition().equals(Position.WEST))
        {
            flashlight.moveTo(137, 107);
            flashlight.lineTo(145, 107);
            flashlight.lineTo(148, 104);
            flashlight.lineTo(148, 113); 
            flashlight.lineTo(145, 110);
            flashlight.lineTo(137, 110);
            flashlight.closePath();
            
            light = new Arc2D.Double(95, 58, 100, 100, -25, 50, Arc2D.PIE);
        }
        else
        {
            flashlight.moveTo(360, 113);
            flashlight.lineTo(360, 104);
            flashlight.lineTo(363, 107);
            flashlight.lineTo(368, 107);
            flashlight.lineTo(368, 110);
            flashlight.lineTo(363, 110);
            flashlight.closePath();
            
            light = new Arc2D.Double(312, 58, 100, 100, 155, 50, Arc2D.PIE);
        }
    }
    
    private class StickFigure
    {
        StickFigure(String name)
        {
            this.name = name;
            location = new Point(0, 0);
            shapeSetup();
        }
        
        StickFigure(String name, Point p)
        {
            this.name = name;
            location = p;
            shapeSetup();
        }
        
        private void shapeSetup()
        {
            head = new Ellipse2D.Double(location.x, location.y, 20, 20);
            body = new Line2D.Double(location.x + 10, location.y + 20,
                    location.x + 10, location.y + 40);
            arms = new Line2D.Double(location.x, location.y + 25,
                    location.x + 20, location.y + 25);
            lLeg = new Line2D.Double(location.x + 10, location.y + 40,
                    location.x, location.y + 50);
            rLeg = new Line2D.Double(location.x + 10, location.y + 40,
                    location.x + 20, location.y + 50);
        }
        
        public void setLocation(Point p)
        {
            location = p;
            shapeSetup();
        }
        
        public Point getLocation()
        {
            return location;
        }
        
        public void draw(Graphics2D g2)
        {
            g2.setColor(Color.white);
            g2.fill(head);
            g2.draw(body);
            g2.draw(arms);
            g2.draw(lLeg);
            g2.draw(rLeg);
            g2.setFont(new Font("Sans Serif", Font.BOLD, 12));
            g2.drawString(name, location.x, location.y + 65);
        }
        private Ellipse2D head;
        private Line2D body;
        private Line2D arms;
        private Line2D lLeg;
        private Line2D rLeg;
        private Point location;
        private String name;
    }
}
