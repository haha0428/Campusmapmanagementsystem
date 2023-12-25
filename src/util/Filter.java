package util;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Filter {

    public static void filter(JTextField textField){
        AbstractDocument document = (AbstractDocument)textField.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // 过滤非数字字符
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < text.length(); i++) {
                    char ch = text.charAt(i);
                    if (Character.isDigit(ch)) {
                        builder.append(ch);
                    }
                }

                // 在指定位置替换文本
                fb.replace(offset, length, builder.toString(), attrs);
            }
        });
    }
}
