package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CacheView {
    private JPanel panel1;
    private JButton directCache;
    private JButton setAsociativaCache;
    private JTextField tfCacheSize;
    private JTextField tfMemorySize;
    private JTextField tfOffsetBits;
    private JButton asociativaCache;

    public CacheView() {

        final JFrame newFrame = new JFrame("Cache Operations");
        newFrame.setSize(600,600);
        newFrame.getContentPane().add(panel1);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);

        directCache.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                newFrame.dispose();
                DirectCacheView directCacheView = new DirectCacheView();
            }
        });
        asociativaCache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFrame.dispose();
                AsociativeCacheView asociativeCacheView = new AsociativeCacheView();
            }
        });
        setAsociativaCache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFrame.dispose();
                SetAssociativeCacheView setAssociativeCacheView = new SetAssociativeCacheView();
            }
        });
    }
}
