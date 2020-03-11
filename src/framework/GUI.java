package framework;

import graph.DequeAdder;
import graph.Vertex;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * A class that creates GUI components for solving search problems.
 * @author Zachary Foede
 */
public class GUI extends JComponent {
    
    public GUI(Problem problem, Canvas canvas, Canvas finalState)
    {
        this.problem = problem;
        this.canvas = canvas;
        this.finalCanvas = finalState;
        moveCount = 0;
        //Divide into three sections
        JPanel intro = new JPanel();        
        JPanel problemGUI = new JPanel();        
        JPanel reset = new JPanel();
        
        //intro text
        JTextArea introText = new JTextArea();
        introText.setText(problem.getIntroduction());
        introText.setFont(new Font("Courier New", Font.BOLD, 14));
        introText.setEditable(false);
    
        final JTextArea probToString = new JTextArea();
        
        //Graphical representation panel
        JPanel graphicsPanel = new JPanel();
        graphicsPanel.setBorder(new TitledBorder("Current State"));
        graphicsPanel.add(this.canvas);
        
        //Final state
        JPanel fState = new JPanel();
        fState.setBorder(new TitledBorder("Final State"));
        fState.add(finalState);
        
        //Automatic solver
        JPanel Solver = new JPanel();
        
        JPanel stats = new JPanel();
        stats.setLayout(new GridLayout(0, 1));
        final JLabel length = new JLabel();
        length.setText("Solution length: ");
        final JLabel dOps = new JLabel();
        dOps.setText("# of queque ops: ");
        final JLabel maxSize = new JLabel();
        maxSize.setText("Max queque size: ");
        stats.add(length);
        stats.add(dOps);
        stats.add(maxSize);
        stats.setPreferredSize(new Dimension(300, stats.getPreferredSize().height));
        final JButton solve = new JButton();
        solve.setText("Solve");
        final JButton nextMove = new JButton();
        final JButton allMoves = new JButton();
        nextMove.setText("Show next move");
        nextMove.setEnabled(false);
        nextMove.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (!getProblem().getSolution().isEmpty())
                {
                    Vertex nextState = getProblem().getSolution().pop();
                    getProblem().setCurrentState((State) nextState);
                    getCanvas().setCurrState((State) nextState);
                    repaint();
                    if (getProblem().getSolution().isEmpty())
                    {
                        nextMove.setEnabled(false);
                        allMoves.setEnabled(false);
                    }
                }
            }
        });
        
        
        allMoves.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                while (!getProblem().getSolution().isEmpty())
                {
                    Vertex nextState = getProblem().getSolution().pop();
                    getProblem().setCurrentState((State) nextState);
                    getCanvas().setCurrState((State) nextState);
                    repaint();
                    if (getProblem().getSolution().isEmpty())
                    {
                        nextMove.setEnabled(false);
                        allMoves.setEnabled(false);
                    }
                }
            }
        });
        
        JPanel movesPanel = new JPanel();
        movesPanel.setLayout(new GridLayout(0, 1));
        movesPanel.add(nextMove);
        movesPanel.add(allMoves);
        
        allMoves.setEnabled(false);
        allMoves.setText("Show all moves");
        Solver.add(solve);        
        Solver.add(stats);
        Solver.add(movesPanel);
        Solver.add(reset);

        
        
        //reset button
        JButton resetButton = new JButton();
        resetButton.setText("RESET");        
        
        
        //problem toString field
        probToString.setText(problem.getCurrentState().toString());
        probToString.setFont(new Font("Courier New", Font.BOLD, 24));
        probToString.setEditable(false);
        JPanel probToStringPanel = new JPanel();
        probToStringPanel.add(probToString);
        probToStringPanel.setBorder(new TitledBorder("Current State"));
        
        
        //Buttons for problems
        final JPanel buttons = new JPanel();
        buttons.setBorder(BorderFactory.createTitledBorder("Possible Moves"));
        GridLayout buttonLayout = new GridLayout(0, 1);
        buttonLayout.setVgap(5);
        buttons.setLayout(buttonLayout);
        for (int i = 0; i < problem.getMoves().size(); i++ )
        {
            Move move = problem.getMoves().get(i);
            JButton temp = new JButton();
            temp.setText(move.getMoveName());
            temp.setName(Integer.toString(i));
            temp.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    JButton source = (JButton) ae.getSource();
                    State temp = getProblem().getCurrentState();
                    if (doMove(getProblem().getMoves().get(Integer.parseInt(source.getName()))))
                    {
                        probToString.setText(getProblem().getCurrentState().toString());
                        getCanvas().setCurrState(getProblem().getCurrentState());
                        getCanvas().setLastState(temp);
                        getCanvas().repaint();
                    }
                    probToString.setText(getProblem().getCurrentState().toString());
                }
            }
            );
            buttons.add(temp);
        }
        
        solve.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (getProblem().search((Vertex) getProblem().getCurrentState()))
                {
                    solve.setEnabled(false);
                    length.setText("Solution length: " + getProblem().getSolution().size());
                    dOps.setText("# of queue ops: " + getProblem().getQueueOps());
                    maxSize.setText("Max queue size: " + getProblem().getMaxQueueSize());
                    nextMove.setEnabled(true);
                    allMoves.setEnabled(true);
                    for (Component c : buttons.getComponents())
                    {
                        c.setEnabled(false);
                    }
                }
                else
                {
                     JOptionPane.showMessageDialog(getCanvas(), "No solution found!");
                }
            }
            
        });
        
        resetButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                moveCount = 0;
                getProblem().reset();
                getProblem().setQueueOps(0);
                getProblem().setMaxQueueSize(0);
                solve.setEnabled(true);
                nextMove.setEnabled(false);
                allMoves.setEnabled(false);
                for (Component c : buttons.getComponents())
                {
                    c.setEnabled(true);
                }
                length.setText("Solution length: ");
                dOps.setText("# of deque ops: ");
                maxSize.setText("Max deque size: ");
                //probToString.setText(getProblem().getCurrentState().toString());
                //getCanvas().setCurrState(getProblem().getCurrentState());
                if (getProblem().usesChooser())
                {
                    ProblemChooser c = new ProblemChooser(getProblem().getInitialStates(),
                                       getProblem().getFinalStates(),
                                       getProblem().getMoveCounts());
                    State newState = c.getStart();
                    State newFinal = c.getFinal();
                    probToString.setText(newState.toString());
                    getProblem().setCurrentState(newState);
                    getProblem().setFinalState(newFinal);
                    getCanvas().setCurrState(newState);
                    getFinalCanvas().setCurrState(newFinal);
                    getFinalCanvas().repaint();
                }
                else    //No chooser
                {
                    probToString.setText(getProblem().getCurrentState().toString());
                    getCanvas().setCurrState(getProblem().getCurrentState());
                }
                getCanvas().setLastState(null);
                getCanvas().repaint();
            }
        }
        );
        
        
        GridBagLayout GUILayout = new GridBagLayout();
        setLayout(GUILayout);
        GridBagConstraints constraints = new GridBagConstraints();
        
        //Add intro components
        intro.add(introText, constraints);
        //Add main GUI components
        //problemGUI.add(probToStringPanel, BorderLayout.CENTER);
        problemGUI.add(graphicsPanel);
        problemGUI.add(buttons, BorderLayout.CENTER);
        problemGUI.add(fState);
        problemGUI.setBorder(new EmptyBorder(10, 50, 10, 50));
        //Add reset components
        reset.add(resetButton, BorderLayout.SOUTH);
        
        constraints.insets = new Insets(25, 0, 50, 0);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.weightx = 1;
        add(introText, constraints);
        
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
        constraints.gridy = 2;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        add(problemGUI, constraints);
        
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridy = 3;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        add(Solver, constraints);
    }
    
    // private methods and instance fields go here
    /**
     * Processes a given move for the current problem.
     * @param move the move to be attempted
     * @return true if the move was valid, false otherwise
     */
    private boolean doMove(Move move)
    {
        State newState = move.doMove(problem.getCurrentState());
        if (newState != null)
        {
            problem.setCurrentState(newState);
            moveCount++;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Illegal Move", null, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (problem.success())
        {
            JOptionPane.showMessageDialog(this, "Congratulations. You"
                    + " Solved the problem in " + Integer.toString(moveCount)
                    + " moves.");
        }
        return true;
    }
    
    private Problem getProblem()
    {
        return problem;
    }
    
    private Canvas getCanvas()
    {
        return canvas;
    }
    
    private Problem problem;
    private int moveCount;
    final private Canvas canvas;
    final private Canvas finalCanvas;

    public Canvas getFinalCanvas() {
        return finalCanvas;
    }
    private DequeAdder searchMethod;
    private Stack<Vertex> solution;

    public Stack<Vertex> getSolution() {
        return solution;
    }

    public void setSolution(Stack<Vertex> solution) {
        this.solution = solution;
    }
}
