import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SideInfo extends JPanel{
        /**
         * Serial Version UID.
         */
        private static final long serialVersionUID = 2181495598854992747L;

        /**
         * The number of rows and columns in the preview window. Set to
         * 5 because we can show any piece with some sort of padding.
         */
        private static final int TILE_COUNT = 5;

        /**
         * The number of pixels used on a small insets (generally used for categories).
         */
        private static final int SMALL_INSET = 20;

        /**
         * The number of pixels used on a large insets.
         */
        private static final int LARGE_INSET = 40;

        /**
         * The y coordinate of the stats category.
         */
        private static final int STATS_INSET = 175;

        /**
         * The y coordinate of the controls category.
         */
        private static final int CONTROLS_INSET = 300;

        /**
         * The number of pixels to offset between each string.
         */
        private static final int TEXT_STRIDE = 25;

        /**
         * The small font.
         */
        private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 11);

        /**
         * The large font.
         */
        private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 13);

        /**
         * The color to draw the text and preview box in.
         */
        private static final Color DRAW_COLOR = new Color(128, 192, 128);

        /**
         * The game instance.
         */
        private Game game;


        public SideInfo(Game game, int height) {
            this.game = game;

            setPreferredSize(new Dimension(200, height));
            setBackground(Color.BLACK);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Set the color for drawing.
            g.setColor(DRAW_COLOR);

            /*
             * This variable stores the current y coordinate of the string.
             * This way we can re-order, add, or remove new strings if necessary
             * without needing to change the other strings.
             */
            int offset;

            /*
             * Draw the "Stats" category.
             */
            g.setFont(LARGE_FONT);
            g.drawString("Stats", SMALL_INSET, offset = STATS_INSET);
            g.setFont(SMALL_FONT);
            g.drawString("Time: " + game.getTimePassed(), LARGE_INSET, offset += TEXT_STRIDE);
            g.drawString("Score: " + game.getScore(), LARGE_INSET, offset += TEXT_STRIDE);

            /*
             * Draw the "Controls" category.
             */
            g.setFont(LARGE_FONT);
            g.drawString("Controls", SMALL_INSET, offset = CONTROLS_INSET);
            g.setFont(SMALL_FONT);
            g.drawString("Move Left: ←", LARGE_INSET, offset += TEXT_STRIDE);
            g.drawString("Move Right: →", LARGE_INSET, offset += TEXT_STRIDE);
            g.drawString("Move Down: Ctrl", LARGE_INSET, offset += TEXT_STRIDE);
            g.drawString("Rotate Anticlockwise: ↑", LARGE_INSET, offset += TEXT_STRIDE);
            g.drawString("Rotate Clockwise: ↓", LARGE_INSET, offset += TEXT_STRIDE);
            g.drawString("Drop: Space", LARGE_INSET, offset += TEXT_STRIDE);
            g.drawString("Pause Game: Esc", LARGE_INSET, offset += TEXT_STRIDE);

        }
}

