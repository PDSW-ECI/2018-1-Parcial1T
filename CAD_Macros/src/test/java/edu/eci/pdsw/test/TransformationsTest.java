/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.test;

import eci.pdsw.draw.controller.Controller;
import eci.pdsw.draw.model.ElementType;
import eci.pdsw.draw.model.Shape;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class TransformationsTest {
    
    public TransformationsTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    //Method : Duplicate
    
    @Test
    public void duplicateTest(){
        Controller guictrl=new Controller();        
        guictrl.setSelectedElementType(ElementType.Line);
        guictrl.addShapeFromScreenPoints(new java.awt.Point(10,10),new java.awt.Point(100, 100));
        guictrl.addShapeFromScreenPoints(new java.awt.Point(20,20),new java.awt.Point(120, 120));
        
        guictrl.duplicateShapes();
        
        
        assertEquals("duplicateShapes in not duplicating the amount of shapes",guictrl.getShapes().size(), 4);
        
        List<Shape> shapes=guictrl.getShapes();
        
        
        for (Shape si:shapes){
            int refcount=0;
            for (Shape sj:shapes){
                if (si==sj) refcount++;
            }
            if (refcount>1){
                fail("duplicateShapes method is generating duplicated references");                        
            }
        }
        
    }

    //Method : rotateSelectedShape

    @Test
    /*ClasesDeEquivalencia para rotar 90° recta (r) con segmento Horizontal
    *r.punto1.y==r.punto2.y ^ r.punto1.x != r.punto1.x
    *PruebaEquivalencia - Correcta/Estándar
    *Resultado : Recta con segmento vertical - r.punto1.x==r.punto2.x ^ r.punto1.y != r.punto2.y
    */
    public void rotateTestEquiSegH(){
        Controller guictrl=new Controller();
        guictrl.setSelectedElementType(ElementType.Line);
        guictrl.addShapeFromScreenPoints(new java.awt.Point(10,100),new java.awt.Point(150, 100));
        guictrl.rotateSelectedShape(0);
        Float x1 = guictrl.getShapes().get(0).getPoint1().getX();
        Float y1 = guictrl.getShapes().get(0).getPoint1().getY();
        Float x2 = guictrl.getShapes().get(0).getPoint2().getX();
        Float y2 = guictrl.getShapes().get(0).getPoint2().getY();
        assertTrue("Rotar Segmento Horizontal 90 grados; Recta vertical Xs igual, Ys desiguales", Math.round(x1)==Math.round(x2) && Math.round(y1)!=Math.round(y2));
    }
    
    @Test
    /*ClasesDeEquivalencia para rotar 90° recta (r) con segmento vertical
    *r.punto1.x==r.punto2.x ^ r.punto1.y != r.punto2.y
    *PruebaEquivalencia - Correcta/Estándar
    *Resultado : Recta con segmento Horizontal - r.punto1.y==r.punto2.y ^ r.punto1.x != r.punto1.x
    */
    public void rotateTestEquiSegV(){
        Controller guictrl=new Controller();
        guictrl.setSelectedElementType(ElementType.Line);
        guictrl.addShapeFromScreenPoints(new java.awt.Point(50,10),new java.awt.Point(50, 100));
        guictrl.rotateSelectedShape(0);
        Float x1 = guictrl.getShapes().get(0).getPoint1().getX();
        Float y1 = guictrl.getShapes().get(0).getPoint1().getY();
        Float x2 = guictrl.getShapes().get(0).getPoint2().getX();
        Float y2 = guictrl.getShapes().get(0).getPoint2().getY();
        assertTrue("Rotar Segmento Vertical 90 grados; Recta horizontal Ys igual, Xs desiguales", Math.round(x1)!=Math.round(x2) && Math.round(y1)==Math.round(y2));
    }

    @Test
    /*ClasesDeEquivalencia para rotar 90° figura (r) con segmento diagonal en enmarcador cuadrado
    *Abs(r.punto1.x - r.punto2.x) = Abs(r.punto1.y - r.punto2.y)
    *PruebaEquivalencia - Correcta/Estándar
    *Resultado : Recta con segmento Diagonal en enmarcador cuadrado - |r.punto1.x - r.punto2.x| == |r.punto1.y – r.punto2.y|
    */
    public void rotateTestEquiSegDC(){
        Controller guictrl=new Controller();
        guictrl.setSelectedElementType(ElementType.Line);
        guictrl.addShapeFromScreenPoints(new java.awt.Point(100,70),new java.awt.Point(50, 120));
        guictrl.rotateSelectedShape(0);
        Float x1 = guictrl.getShapes().get(0).getPoint1().getX();
        Float y1 = guictrl.getShapes().get(0).getPoint1().getY();
        Float x2 = guictrl.getShapes().get(0).getPoint2().getX();
        Float y2 = guictrl.getShapes().get(0).getPoint2().getY();
        assertTrue("Rotar Segmento Diagonal enmarcador cuadrado; se conserva el cuadrado", Math.abs(y1-y2)==Math.abs(x1-x2) && (x1==50 || x2==50) && (y1!=70 && y2!=70));
    }
    
    @Test
    /*ClasesDeEquivalencia para rotar 90° figura (r) con segmento diagonal en enmarcador rectangular
    *r.punto1.y!=r.punto2.y ^ r.punto1.x != r.punto1.x
    *PruebaEquivalencia - Correcta/Estándar
    *Resultado : Recta con segmento Diagonal en enmarcador rectangular
    *(|r.oldpunto1.x – r.oldpunto2.x| == |r.newpunto1.y – r.newpunto2.y|) ^ 
    *(|r.oldpunto1.y – r.oldpunto2.y| == |r.newpunto1.x – r.newpunto2.x|)
    */
    public void rotateTestEquiSegDR(){
        Controller guictrl=new Controller();
        guictrl.setSelectedElementType(ElementType.Rectangle);
        guictrl.addShapeFromScreenPoints(new java.awt.Point(50,140),new java.awt.Point(80, 60));
        guictrl.rotateSelectedShape(0);
        Float x1 = guictrl.getShapes().get(0).getPoint1().getX();
        Float y1 = guictrl.getShapes().get(0).getPoint1().getY();
        Float x2 = guictrl.getShapes().get(0).getPoint2().getX();
        Float y2 = guictrl.getShapes().get(0).getPoint2().getY();
        
        assertTrue("Rotar Segmento Diagonal enmarcador rectangular; las diferencia de Xold es igual Ynew", (Math.abs(80-50)==Math.abs(y1-y2)) && (Math.abs(140-60)==Math.abs(x1-x2)));
    }
    
    
    @Test
    /*ClasesDeFrontera para rotar 90° recta (r) con segmento Horizontal
    *r.punto1.y==r.punto2.y ^ r.punto1.x != r.punto1.x
    *PruebaFrontera - Correcta/Estándar
    *Resultado : Recta con segmento vertical - min(oldpunto.x) --> oldpuntoi.y == newpuntoi.y && oldpuntoi.x == newpuntoi.y
    */
    public void rotateTestFrontSegH(){
        Controller guictrl=new Controller();
        guictrl.setSelectedElementType(ElementType.Line);
        guictrl.addShapeFromScreenPoints(new java.awt.Point(10,100),new java.awt.Point(150, 100));
        guictrl.rotateSelectedShape(0);
        Float x1 = guictrl.getShapes().get(0).getPoint1().getX();
        Float y1 = guictrl.getShapes().get(0).getPoint1().getY();
        Float x2 = guictrl.getShapes().get(0).getPoint2().getX();
        Float y2 = guictrl.getShapes().get(0).getPoint2().getY();
        //min(10, 150) := 10 (punto1);
        assertTrue("Rotar Segmento Horizontal 90 grados; el punto de la izquierda sigue siendo el mismo", Math.round(x1) == 10 && Math.round(y1)==100 && Math.round(x1)==Math.round(x2));
    }
    
    @Test
    /*ClasesDeEquivalencia para rotar 90° recta (r) con segmento vertical
    *r.punto1.x==r.punto2.x ^ r.punto1.y != r.punto2.y
    *PruebaEquivalencia - Correcta/Estándar
    *Resultado : Recta con segmento Horizontal -  min(oldpunto.y) --> oldpuntoi.y == newpuntoi.y && oldpuntoi.x == newpuntoi.y
    */
    public void rotateTestFrontSegV(){
        Controller guictrl=new Controller();
        guictrl.setSelectedElementType(ElementType.Line);
        guictrl.addShapeFromScreenPoints(new java.awt.Point(50,10),new java.awt.Point(50, 100));
        guictrl.rotateSelectedShape(0);
        Float x1 = guictrl.getShapes().get(0).getPoint1().getX();
        Float y1 = guictrl.getShapes().get(0).getPoint1().getY();
        Float x2 = guictrl.getShapes().get(0).getPoint2().getX();
        Float y2 = guictrl.getShapes().get(0).getPoint2().getY();
        //max(10,100) := 100(punto2)
        assertTrue("Rotar Segmento Vertical 90 grados; el punto de abajo sigue siendo el mismo", Math.round(x2) == 50 && Math.round(y2)==100 && Math.round(y2)==Math.round(y1));
    }

    @Test
    /*ClasesDeEquivalencia para rotar 90° figura (r) con segmento diagonal en enmarcador cuadrado
    *Abs(r.punto1.x - r.punto2.x) = Abs(r.punto1.y - r.punto2.y)
    *PruebaEquivalencia - Correcta/Estándar
    *Resultado : Recta con segmento Diagonal en enmarcador cuadrado - max(oldpoint.y) == min(newpoint.y) ^ min(oldpoint.x) == min(newpoint.x) ^ max(oldpoint.x) == max(newpoint.x)
    */
    public void rotateTestFrontSegDC(){
        Controller guictrl=new Controller();
        guictrl.setSelectedElementType(ElementType.Line);
        guictrl.addShapeFromScreenPoints(new java.awt.Point(50,120),new java.awt.Point(100, 70));
        guictrl.rotateSelectedShape(0);
        Float x1 = guictrl.getShapes().get(0).getPoint1().getX();
        Float y1 = guictrl.getShapes().get(0).getPoint1().getY();
        Float x2 = guictrl.getShapes().get(0).getPoint2().getX();
        Float y2 = guictrl.getShapes().get(0).getPoint2().getY();
        //max(70,120) := 120 (point2)  / min(50,100) := 50 (point1) / max(50,100) := 100 (point2)
        assertTrue("Rotar Segmento Diagonal enmarcador cuadrado; en las esquinas del enmarcador son iguales", Math.min(y1,y2)==120 && Math.min(x1,x2)==50 && Math.max(x1,x2)==100);
    }
    
    @Test
    /*ClasesDeEquivalencia para rotar 90° figura (r) con segmento diagonal en enmarcador rectangular
    *r.punto1.y!=r.punto2.y ^ r.punto1.x != r.punto1.x
    *PruebaEquivalencia - Correcta/Estándar
    *Resultado : Recta con segmento Diagonal en enmarcador rectangular - max(oldpoint.y) == min(newpoint.y) ^ min(oldpoint.x) == min(newpoint.x)
    */
    public void rotateTestFrontSegDR(){
        Controller guictrl=new Controller();
        guictrl.setSelectedElementType(ElementType.Rectangle);
        guictrl.addShapeFromScreenPoints(new java.awt.Point(50,140),new java.awt.Point(80, 60));
        guictrl.rotateSelectedShape(0);
        Float x1 = guictrl.getShapes().get(0).getPoint1().getX();
        Float y1 = guictrl.getShapes().get(0).getPoint1().getY();
        Float x2 = guictrl.getShapes().get(0).getPoint2().getX();
        Float y2 = guictrl.getShapes().get(0).getPoint2().getY();
        //max(60,140) := 140 (point2) / min(50,80) := 50 (point1)
        assertTrue("Rotar Segmento Diagonal enmarcador rectangular; en las esquinas del enmarcador son iguales", Math.min(y1,y2)==140 && Math.min(x1,x2)==50);
    }

    
}
