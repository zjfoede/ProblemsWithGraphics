/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocks;

import framework.Canvas;
import framework.State;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zach
 */
public class BlocksCanvas extends Canvas
{
    public BlocksCanvas(State state)
    {
        super(state);
        blocks = new ArrayList<Block>();
        background = new RoundRectangle2D.Double(0, 0, 430, 250, 10, 10);
        table = new Line2D.Double(50, 200, 380, 200);
        BlockState s = (BlockState) state;
        blockSetup(s);
        setPreferredSize(new Dimension(430, 250));
    }
    
    private void blockSetup(BlockState s)
    {
        blocks.clear();
        int maxSize = max(s.getP().size(), s.getQ().size());
        maxSize = max(maxSize, s.getR().size());  //Find the largest stack;
        for (int i = 0; i < maxSize; i++)
        {
            if (i < s.getP().size())
            {
                blocks.add(new Block(s.getP().elementAt(i), new Point(115, 178 - 20*i)));
            }
            if (i < s.getQ().size())
            {
                blocks.add(new Block(s.getQ().elementAt(i), new Point(205, 178 - 20*i)));
            }
            if (i < s.getR().size())
            {
                blocks.add(new Block(s.getR().elementAt(i), new Point(295, 178 - 20*i)));
            }
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        blockSetup((BlockState) getCurrState());
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fill(background);
        g2.setColor(Color.black);
        g2.draw(table);
        g2.drawString("p", 120, 220);
        g2.drawString("q", 210, 220);
        g2.drawString("r", 300, 220);
        for (Block b : blocks)
        {
            b.draw(g2);
        }
    }
    
    private RoundRectangle2D background;
    private Line2D table;
    private List<Block> blocks;
    
    private class Block
    {
        public Block(Character label, Point loc)
        {
            this.label = label;
            this.loc = loc;
            tile = new Rectangle2D.Double(loc.x, loc.y, 20, 20);
        }
        
        public void draw(Graphics2D g2)
        {
            g2.setColor(Color.blue);
            g2.fill(tile);
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(4));
            g2.draw(tile);
            g2.setColor(Color.white);
            g2.setFont(new Font("Sans Serif", Font.BOLD, 16));
            g2.drawString(label.toString(), loc.x + 4, loc.y + 16);
        }
        
        private Rectangle2D tile;
        private Character label;
        private Point loc;
    }
    
}
