package org.wayriad.waymemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TransparentFrame extends JFrame
{
 public TransparentFrame()
 {
 setTitle("Transparent JFrame Demo");
 setSize(800,600);
  //For Java 1.7 or above
 setOpacity(0.4f);
  //For lower java versions
 }
 
}