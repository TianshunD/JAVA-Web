package VerifyImg;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkImg")
public class ServletVeriImg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //create img
        int width = 100;
        int height = 50;
        BufferedImage img = new BufferedImage(100,50,BufferedImage.TYPE_INT_RGB);
        Graphics gra = img.getGraphics(); //画笔对象
        gra.setColor(Color.PINK);
        gra.fillRect(0,0,width,height); //填充背景颜色

        gra.setColor(Color.BLUE);
        gra.drawRect(0,0,width-1,height-1); //add border

        //add verify code
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random ran = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 1; i < 5; i++) {
            char cha = str.charAt(ran.nextInt(str.length()));
            code.append(cha);
            gra.drawString(cha+"",20*i,25);
        }

        String str_code = code.toString();
        request.getSession().setAttribute("veriCode",str_code);


        //画干扰线
        gra.setColor(Color.GREEN);
        for (int i = 1; i < 7; i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            gra.drawLine(x1,y1,x2,y2);
        }

        //output to page
        ImageIO.write(img,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
