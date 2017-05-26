// 导出打印单选按钮及复选框 
package com.fr.function;  
  
import java.awt.Color;  
import java.awt.FontMetrics;  
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.Image;  
import com.fr.base.BaseUtils;  
import com.fr.base.GraphHelper;
import com.fr.base.Style;  
import com.fr.base.AbstractPainter;  
import com.fr.general.FArray;
import com.fr.general.FRFont;
import com.fr.script.AbstractFunction;  
import com.fr.stable.Primitive;
import com.fr.stable.StringUtils;
  
public class Widget2Image extends AbstractFunction {  
    public Object run(Object[] args) {  
        if (args.length < 3)  
            return Primitive.NULL;  
        // 第一个参数：控件类型，不区分大小写  
        String type = args[0].toString().toLowerCase();  
        if (!("checkbox".equals(type) || "radiobutton".equals(type)))  
            return Primitive.ERROR_VALUE;  
        // 第二个参数：控件按钮个数  
        int num = Integer.parseInt(args[1].toString());  
        // 第三个参数：按钮组的值，哪些被选中  
        String selection = args[2].toString();  
        // 第四个参数:可选参数，按钮组对应的显示值数组  
        FArray textArray = new FArray();  
        if (args.length == 4 && args[3] instanceof FArray) {  
            textArray = (FArray) args[3];  
        }  
        return new WidgetPaint(type, num, selection, textArray);  
    }  
  
    public static class WidgetPaint extends AbstractPainter {  
        public static String CHECK_ON = "/com/fr/web/images/checkon.gif";  
        public static String CHECK_OFF = "/com/fr/web/images/checkoff.gif";  
        public static String RADIO_ON = "/com/fr/web/images/radioon.gif";  
        public static String RADIO_OFF = "/com/fr/web/images/radiooff.gif";  
        public static FRFont DEFUALT_FONT = FRFont.getInstance();  
        public static FontMetrics FontMetrics = GraphHelper  
                .getFontMetrics(DEFUALT_FONT);  
        private String type;  
        private int num;  
        private String selection;  
        private FArray textArray;  
        {  
            DEFUALT_FONT = DEFUALT_FONT.applyForeground(Color.BLACK);  
        }  
  
        public WidgetPaint(String type, int num, String selection,  
                FArray textArray) {  
            this.type = type;  
            this.num = num;  
            this.selection = selection;  
            this.textArray = textArray;  
        }  
  
        private String resolveText(int i) {  
            if (i < this.textArray.length()) {  
                return this.textArray.elementAt(i).toString();  
            }  
            return StringUtils.EMPTY;  
        }  
  
        public void paint(Graphics g, int width, int height, int resolution,  
                Style style) {  
            String OFF = CHECK_OFF;  
            String ON = CHECK_ON;  
            if ("radiobutton".equals(type)) {  
                OFF = RADIO_OFF;  
                ON = RADIO_ON;  
            }  
            Image[] checkOFFON = { BaseUtils.readImage(OFF),  
                    BaseUtils.readImage(ON) };  
            int[] imgWidths = { checkOFFON[0].getWidth(null),  
                    checkOFFON[1].getWidth(null) };  
            int[] imgHeights = { checkOFFON[0].getHeight(null),  
                    checkOFFON[1].getHeight(null) };  
            Graphics2D g2d = (Graphics2D) g;  
            g2d.setFont(FRFont.getInstance());  
            g2d.setPaint(Color.BLACK);  
            int x = 2;  
            int y = (height - imgHeights[0]) / 2;  
            String select = selection;  
            for (int i = 0; i < num; i++) {  
                int bit = Integer.parseInt(select.substring(i, i + 1));  
                g2d.drawImage(checkOFFON[bit], x, y, imgWidths[bit],  
                        imgHeights[bit], null);  
                x += imgWidths[bit] + 2;  
                String text = resolveText(i);  
                g2d.setBackground(Color.BLACK);  
                g2d.drawString(text, (float) x, (float) (y + FontMetrics  
                        .getAscent()));  
                x += FontMetrics.stringWidth(text) + 2;  
            }  
        }  
    }  
} 