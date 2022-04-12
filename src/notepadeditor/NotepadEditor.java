/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadeditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;


public class NotepadEditor implements ActionListener , WindowListener
{
    JMenuItem neww , open , save , saveas , exit , undo , cut , copy , paste , selected , delete , dateandtime , font , font_color , background_color , zoomin , zoomout , about_notepad ;
    JTextArea textarea ;
    JFrame jf , font_frame , about_frame ;
    File file ;
    JLabel l ;
    
    JComboBox font_family , font_size , font_style ;
    JButton ok , cancel , ok1 ;
    
    NotepadEditor()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        jf = new JFrame("*Untitle* - Notepad");
        jf.setSize(700, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        
        JMenuBar jmenubar = new JMenuBar();
        
        JMenu file = new JMenu("File");
        
        neww = new JMenuItem("New     Ctrl+N");
        neww.addActionListener(this);
        file.add(neww);
                
        open = new JMenuItem("Open...     Ctrl+O");
        open.addActionListener(this);
        file.add(open);
        
        save = new JMenuItem("Save     Ctrl+S");
        save.addActionListener(this);
        file.add(save);
        
        saveas = new JMenuItem("Save As...     Ctrl+Shift+S");
        saveas.addActionListener(this);
        file.add(saveas);
        
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        file.add(exit);
        
        jmenubar.add(file);
        
        JMenu edit = new JMenu("Edit");
        
        /*
        undo = new JMenuItem("Undo     Ctrl+Z");
        undo.addActionListener(this);
        edit.add(undo);
        */
        
        cut = new JMenuItem("Cut       Ctrl+X");
        cut.addActionListener(this);
        edit.add(cut);
        
        copy = new JMenuItem("Copy     Ctrl+C");
        copy.addActionListener(this);
        edit.add(copy);
        
        paste = new JMenuItem("Paste     Ctrl+V");
        paste.addActionListener(this);
        edit.add(paste);
        
        selected = new JMenuItem("Select All     Ctrl+A");
        selected.addActionListener(this);
        edit.add(selected);
        
        delete = new JMenuItem("Delete");
        delete.addActionListener(this);
        edit.add(delete);
        
        dateandtime = new JMenuItem("Date/Time");
        dateandtime.addActionListener(this);
        edit.add(dateandtime);
        
        jmenubar.add(edit);
        
        JMenu format = new JMenu("Format");
        
        font = new JMenuItem("Font...");
        font.addActionListener(this);
        format.add(font);
        
        font_color = new JMenuItem("Font Color");
        font_color.addActionListener(this);
        format.add(font_color);
        
        background_color = new JMenuItem("Background Color");
        background_color.addActionListener(this);
        format.add(background_color);
        
        jmenubar.add(format);
        
        /*
        JMenu view = new JMenu("View");
        
        zoomin = new JMenuItem("Zoom In     Ctrl+Plus");
        zoomin.addActionListener(this);
        view.add(zoomin);
        
        zoomout = new JMenuItem("Zoom Out     Ctrl+Minus");
        zoomout.addActionListener(this);
        view.add(zoomout);
        
        jmenubar.add(view);
        */
        
        JMenu help = new JMenu("Help");
        
        about_notepad = new JMenuItem("About Notepad");
        about_notepad.addActionListener(this);
        help.add(about_notepad);
        
        jmenubar.add(help);
        
        jf.setJMenuBar(jmenubar);
        
        
        textarea = new JTextArea();
        
        JScrollPane scrollpane = new JScrollPane(textarea);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        jf.add(scrollpane);
        
        jf.addWindowListener(this);
        
        jf.setVisible(true);
    }

    
    public static void main(String[] args)
    {
        new NotepadEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == neww )
        {
            //textarea.getInputMap().put(KeyStroke.getKeyStroke("control N"), "none");// disable cut
            newFile();
        }
        if(e.getSource() == open )
        {
            //textarea.getInputMap().put(KeyStroke.getKeyStroke("control O"), "none");// disable cut
            openFile();
        }
        if(e.getSource() == save )
        {
            //textarea.getInputMap().put(KeyStroke.getKeyStroke("control S"), "none");
            saveFile();
        }
        if(e.getSource() == saveas )
        {
            saveAsFile();
        }
        if(e.getSource() == exit )
        {
            exitFile();
        }
        /*
        if(e.getSource() == undo )
        {
            undoFile();
        }
        */
        if(e.getSource() == cut )
        {
            textarea.getInputMap().put(KeyStroke.getKeyStroke("control X"), "none");// disable cut
            textarea.cut();
        }
        if(e.getSource() == copy )
        {
            textarea.getInputMap().put(KeyStroke.getKeyStroke("control C"), "none"); // disable copy
            textarea.copy();
        }
        if(e.getSource() == paste )
        {
            textarea.getInputMap().put(KeyStroke.getKeyStroke("control V"), "none"); // disable paste
            textarea.paste();
        }
        if(e.getSource() == selected )
        {
            textarea.selectAll();
        }
        if(e.getSource() == delete )
        {
            textarea.setText("");
        }
        if(e.getSource() == dateandtime )
        {
            dateAndTime();            
        }
        if(e.getSource() == font )
        {
            openFontFrame();
        }
        if(e.getSource() == ok )
        {
            setFontOnTextArea();
        }
        if(e.getSource() == cancel )
        {
            // hide frame
            font_frame.setVisible(false);
        }
        if(e.getSource() == font_color )
        {
            Color c = JColorChooser.showDialog(jf, "Choosen Font Color", Color.black);
            textarea.setForeground(c);
        }
        if(e.getSource() == background_color )
        {
            Color c = JColorChooser.showDialog(jf, "Choosen Background Color", Color.white);
            textarea.setBackground(c);
        }
        /*
        if(e.getSource() == zoomin )
        {
            float x = textarea.getAlignmentX();
            float y = textarea.getAlignmentY();

                if(x<5 && y<5)
                {
                    
                }
                
        }
        if(e.getSource() == zoomout )
        {
            
        }
        */
        if(e.getSource() == about_notepad )
        {
            aboutNotepad();
        }
        if(e.getSource() == ok1 )
        {           
            about_frame.setVisible(false);
        }
    }
    
    void newFile()
    {
        String text = textarea.getText();
        if(! text.equals(""))
        {
            int i = JOptionPane.showConfirmDialog(jf, "Do you want to save this file ? ");
             
            if( i == 0 )
            {
                saveAsFile();
                textarea.setText("");
                jf.setTitle("*Untitled* - Notepad");
            }
            else if( i == 1 )
            {
                textarea.setText("");
                jf.setTitle("*Untitled* - Notepad");
            }
        }
    }
    
    void openFile()
    {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(jf);
        if( result == 0 )
        {
            textarea.setText("");
            file = fileChooser.getSelectedFile();
            jf.setTitle(file.getName());
            try(FileInputStream fis = new FileInputStream(file))
            {
                int i ;
                while( (i = fis.read() ) != -1 )
                {
                    textarea.append(String.valueOf((char)i));
                }
            }
            catch(IOException ee)
            {
                ee.printStackTrace();
            }
        }
    }
    
    void saveFile()
    {
        String title = jf.getTitle();
        
        if( title.equals("*Untitle* - Notepad") )
        {
            saveAsFile();
        }
        else
        {
            String text = textarea.getText();
            try(FileOutputStream fos = new FileOutputStream(file))
            {
                byte[] b = text.getBytes();
                fos.write(b);
            
                //fos.close();
            }
            catch(IOException ee)
            {
               ee.printStackTrace();
            }
        }
    }
    
    void saveAsFile()
    {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(jf);
        if( result == 0 )
        {
            String text = textarea.getText();
            file = fileChooser.getSelectedFile();
            jf.setTitle(file.getName());
            try(FileOutputStream fos = new FileOutputStream(file))
            {
                byte[] b = text.getBytes();
                fos.write(b);
            
                //fos.close();
            }
            catch(IOException ee)
            {
               ee.printStackTrace();
            }
        }
    }
    
    void exitFile()
    {
        String text =textarea.getText();
        
        if( ! text.equals("") )
        {
            int i = JOptionPane.showConfirmDialog(jf, "Do you want to save this file ?");
            
            if( i == 0 )
            {
                JFileChooser filechooser = new JFileChooser();
                int result = filechooser.showSaveDialog(jf);
                if( result == 0 )
                {
                    String textfile = textarea.getText();
                    file = filechooser.getSelectedFile();
                    jf.setTitle(file.getName());
                    try(FileOutputStream fos = new FileOutputStream(file))
                    {
                        byte[] b = textfile.getBytes();
                        fos.write(b);
                        
                        //fos.close();
                    }
                    catch(IOException ee)
                    {
                       ee.printStackTrace();
                    }
                }
                
            }
            else if( i == 1 )
            {
                System.exit(0);
            }
            
        }
        else
        {
            System.exit(0);
        }
    }
    
    void dateAndTime()
    {
        Date date = new Date();
        
        //JOptionPane.showMessageDialog(jf, date);
        //System.out.println(date);
        //textarea.setText(date);
        
        // convert Object to String 
        String s = date.toString();
        textarea.setText(s);
    }
    
    /*
    void undoFile()
    {
        textarea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "none"); // disable cut
        JOptionPane.showMessageDialog(jf, "Undo");
    }
    */
    
    void openFontFrame()
    {       
        font_frame = new JFrame("Choose Font");
        font_frame.setSize(500, 500);
        font_frame.setLocationRelativeTo(jf);
        font_frame.setLayout(null);
        
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        font_family = new JComboBox(fonts);
        font_family.setBounds(50, 100, 100, 30);
        font_frame.add(font_family);
        
        String[] sizes = {"10" , "12" , "14" , "24" , "28" , "34" , "42" , "72" };
        font_size = new JComboBox(sizes);
        font_size.setBounds(170, 100, 100, 30);
        font_frame.add(font_size);
        
        String[] style = {"plain" , "bold" , "italic" , "italic & bold"};
        font_style = new JComboBox(style);
        font_style.setBounds(300, 100, 100, 30);
        font_frame.add(font_style);
        
        ok = new JButton("Ok");
        ok.setBounds(230, 400, 100, 30);
        ok.addActionListener(this);
        font_frame.add(ok);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(350, 400, 100, 30);
        cancel.addActionListener(this);
        font_frame.add(cancel);
        
        font_frame.setVisible(true);
    }
    
    void setFontOnTextArea()
    {
        String fontfamily = (String) font_family.getSelectedItem();
        String fontsize = (String) font_size.getSelectedItem();   // 10 , 12 , 20 , 30
        String fontstyle = (String) font_style.getSelectedItem(); // plain , bold , italic
            
        int style = 0 ;
            
        if(fontstyle.equals("plain"))
        {
            style = 0 ;
        }
        else if(fontstyle.equals("bold"))
        {
            style = 1 ;
        }
        else if(fontstyle.equals("italic"))
        {
            style = 2 ;
        }
        else if(fontstyle.equals("italic & bold"))
        {
            style = 3 ;
        }
            
        Font fontt = new Font(fontfamily , style , Integer.parseInt(fontsize));
        textarea.setFont(fontt);
            
        // hide frame
        font_frame.setVisible(false);
    }
    
    void aboutNotepad()
    {
        JLabel jlabel = new JLabel("This Notepad editor created by Julias Biswas.",JLabel.CENTER);
        jlabel.setFont(new Font("Serif", Font.BOLD, 24));
        jlabel.setAlignmentX(0);
        jlabel.setAlignmentY(0);
        
        
        
        about_frame = new JFrame("About Notepad");
        about_frame.setSize(500, 500);
        about_frame.setResizable(false);
        about_frame.setLocationRelativeTo(jf);
        //about_frame.setLayout(null);
        about_frame.add(jlabel);
        
       /*
        ok1 = new JButton("Ok");
        ok1.setBounds(350, 400, 100, 30);
        ok1.addActionListener(this);      
        about_frame.add(ok1);
        */
        
        
        
        about_frame.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) 
    {
        // System.out.println("windowOpened");
    }

    @Override
    public void windowClosing(WindowEvent e) 
    {
        String text = textarea.getText();
        if( ! text.equals(""))
        {
            int i = JOptionPane.showConfirmDialog(jf, "Do you want to save this file ?");
        
            if( i == 0 )
            {
                JFileChooser filechooser = new JFileChooser();
                int result = filechooser.showSaveDialog(jf);
                if( result == 0 )
                {
                    String textfile = textarea.getText();
                    file = filechooser.getSelectedFile();
                    jf.setTitle(file.getName());
                    try(FileOutputStream fos = new FileOutputStream(file))
                    {
                        byte[] b = textfile.getBytes();
                        fos.write(b);
                        

                        //fos.close();
                    }
                    catch(IOException ee)
                    {
                       //System.out.println(ee.getMessage());
                       ee.printStackTrace();
                    }
                }
            }
            else if( i == 1 )
            {
                System.exit(0);
            }
            /*
            else if( i == 1 )
            {
                System.exit(0);
                //jf.setVisible(true);
                //JOptionPane.showMessageDialog(jf, "cancel button pressed");
            }
            else if( i == 2 )
            {
                jf.setVisible(true);
            }
            */
        }
        else
        {
            System.exit(0);
        }
        
    }

    @Override
    public void windowClosed(WindowEvent e) 
    {
        // System.out.println("windowClosed");
    }

    @Override
    public void windowIconified(WindowEvent e) 
    {
        // System.out.println("windowIconified");
    }

    @Override
    public void windowDeiconified(WindowEvent e) 
    {
        // System.out.println("windowDeiconified");
    }

    @Override
    public void windowActivated(WindowEvent e) 
    {
        // System.out.println("windowActivated");
    }

    @Override
    public void windowDeactivated(WindowEvent e) 
    {
        // System.out.println("windowActivated");
    }
}
