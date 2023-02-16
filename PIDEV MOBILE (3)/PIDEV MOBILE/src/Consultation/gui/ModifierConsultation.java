/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package Consultation.gui;

import Blog.gui.*;
import Produit.gui.*;
import com.codename1.capture.Capture;
import com.codename1.components.ScaleImageLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.mycompany.gui.BaseForm;
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.Consultation;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.services.ServiceBlog;
import com.mycompany.myapp.entities.services.ServiceConsultation;
import com.mycompany.myapp.entities.services.ServiceProduit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ModifierConsultation extends BaseForm {
   String Imagecode;
   String filePath="";

    public ModifierConsultation(Resources res,Form previous,Consultation fi) {
        super("Modifier Consultation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Consultation");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
                add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(2, 
                            facebook, twitter
                    )
                )
        ));

                        
        TextComponent nom= new TextComponent().label("Nom");
        nom.text(fi.getNom());
        add(nom);
                              
        TextComponent prenom= new TextComponent().label("Prenom");
        prenom.text(fi.getPrenom());
        add(prenom);

        ComboBox<String> comboage = new ComboBox<>();
        comboage.addItem("20");
        comboage.addItem("25");
        comboage.addItem("30");
        comboage.addItem("35");
        comboage.addItem("40");
        comboage.addItem("45");
        comboage.addItem("50");
        comboage.addItem("55");
        comboage.setSelectedItem(String.valueOf(fi.getAge()));
        add(comboage);
        
        ComboBox<String> combosexe = new ComboBox<>();
        combosexe.addItem("Masculin");
        combosexe.addItem("Feminin");
        combosexe.setSelectedItem(fi.getSexe());
        add(combosexe);
               
        TextComponent etatphy= new TextComponent().label("Etat Physique");
        etatphy.text(fi.getEtatPhysique());
        add(etatphy);

        ComboBox<String> combocateg= new ComboBox<>();
        combocateg.addItem("Blessure");
        combocateg.addItem("Regime");
        combocateg.addItem("Therapie");
        combocateg.setSelectedItem(fi.getCategorie());
        add(combocateg);
                      
        TextComponent email= new TextComponent().label("Email");
        email.text(fi.getEmail());
        add(email);

        Label lbdate = new Label("date :");
        add(lbdate);
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(fi.getDaterdv());
        add(datePicker);

         
        Button Edit = new Button("Modifier");
        Edit.addActionListener((evt) -> {
                if (etatphy.getText().equals("")||(nom.getText().equals(""))||(prenom.getText().equals("")))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
            ImageIO imgIO = ImageIO.getImageIO();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] ba = out.toByteArray();
            Imagecode = Base64.encode(ba);
            System.out.println(filePath);
            
                    ServiceConsultation sp = new ServiceConsultation();
            fi.setNom(nom.getText());
            fi.setPrenom(prenom.getText());
            fi.setAge(Integer.valueOf(comboage.getSelectedItem()));
            fi.setSexe(combosexe.getSelectedItem());
            fi.setEtatPhysique(etatphy.getText());
            fi.setEmail(email.getText());
           
                                   
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date datecreation = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new 
                        SimpleDateFormat(DateFormatPatterns.ISO8601);

                        fi.setDaterdv(datePicker.getDate());
           
            fi.setCategorie(combocateg.getSelectedItem());
            sp.editConsultation(fi);
            Dialog.show("Success","Consultation Modifier avec success",new Command("OK"));
            new AllConsultation(res).show();
                
            }      
        });
        addStringValue("", FlowLayout.encloseRightMiddle(Edit));
        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
