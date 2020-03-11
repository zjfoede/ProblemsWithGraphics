/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import framework.Canvas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zach
 */
public class PuzzleCanvas extends Canvas
{
    public PuzzleCanvas(PuzzleState state)
    {
        super(state);
        background = new Rectangle2D.Double(0, 0, 330, 330);
        tiles = new ArrayList<Tile>();
        tileSetup();
        setPreferredSize(new Dimension(330, 330));
    }
    
    private void tileSetup()
    {
        tiles.clear();
        PuzzleState s = (PuzzleState) getCurrState();
        int xLoc = 10;
        int yLoc = 10;
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                String label = Integer.toString(s.getTile(x, y));
                if (!label.equals("-1"))
                {
                    tiles.add(new Tile(label, new Point(xLoc, yLoc)));
                }
                xLoc += 105;
            }
            xLoc = 10;
            yLoc += 105;
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.clearRect(0, 0, 330, 330);
        g2.setColor(Color.white);
        g2.fill(background);
        tileSetup();
        for (Tile t : tiles)
        {
            t.draw(g2);
        }
    }
    
    private class Tile
    {
        public Tile(String label, Point loc)
        {
            this.label = label;
            this.loc = loc;
            tile = new Rectangle2D.Double(loc.x, loc.y, 100, 100);
        }
        
        public void draw(Graphics2D g2)
        {
            g2.setColor(Color.blue);
            g2.fill(tile);
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(4));
            g2.draw(tile);
            g2.setColor(Color.white);
            g2.setFont(new Font("Sans Serif", Font.BOLD, 80));
            g2.drawString(label.toString(), loc.x + 25, loc.y + 80);
        }
        
        private Rectangle2D tile;
        private String label;
        private Point loc;
    }
    
    private Rectangle2D background;
    private List<Tile> tiles;
}
