package mx.edu.ittepic.ladm_u2_practica1_alvaradomartinezantoniodejesus_coposdenieve

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Lienzo (este:MainActivity) : View(este){
    /*ancho: 1080
      alto: 1976
     */
    var este = este
    var coposGrandes = Array<Copo>(60, {Copo(this,40f)})
    var coposBebes = Array<Copo>(60, {Copo(this,20f)})
    var contador = 0

    val corrutina = GlobalScope.launch {
        while (true) {
            este.runOnUiThread {
                invalidate()
                if(contador>=300){
                    contador = 0
                }
                contador++
            }
            delay(20L)
        }
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        //timer.start()

        c.drawColor(Color.rgb(0,204,204))
        dibujarNubes(c)
        dibujarMontañaIzquierda(c)
        dibujarMontañaDerecha(c)
        dibujarSuelo(c)
        dibujarCasa(c)
        dibujarArbol(c)

        if(contador<=100){
            for (cop in coposGrandes) {
                cop.caerConViento()
                cop.pintar(c)
            }
            for (cop in coposBebes) {
                cop.caerConViento()
                cop.pintar(c)
            }
        }
        if(contador>100 && contador<=200){
            coposGrandes = Array(90,{Copo(this,40f)})
            coposBebes = Array(90,{Copo(this,20f)})
            for (cop in coposGrandes) {
                cop.caerSinViento()
                cop.pintar(c)
            }
            for (cop in coposBebes) {
                cop.caerSinViento()
                cop.pintar(c)
            }
        }
        if(contador>200){
            coposGrandes = Array(120,{Copo(this,40f)})
            coposBebes = Array(120,{Copo(this,20f)})
            for (cop in coposGrandes) {
                cop.caerFuerte()
                cop.pintar(c)
            }
            for (cop in coposBebes) {
                cop.caerFuerte()
                cop.pintar(c)
            }
        }


    }

    private fun dibujarCasa(c: Canvas) {
        val p = Paint()
        p.color = Color.rgb(139,69,19)
        c.drawRect((width*0.30).toFloat(),(height*0.65).toFloat(),(width*0.75).toFloat(),(height*0.85).toFloat(),p)
        p.strokeWidth = 10f
        c.drawLine((width*0.30).toFloat(),(height*0.65).toFloat(),(width*0.40).toFloat(),(height*0.58).toFloat(),p)
        c.drawLine((width*0.75).toFloat(),(height*0.65).toFloat(),(width*0.85).toFloat(),(height*0.58).toFloat(),p)
        c.drawLine((width*0.40).toFloat(),(height*0.58).toFloat(),(width*0.85).toFloat(),(height*0.58).toFloat(),p)
        c.drawLine((width*0.85).toFloat(),(height*0.78).toFloat(),(width*0.85).toFloat(),(height*0.58).toFloat(),p)
        c.drawLine((width*0.75).toFloat(),(height*0.85).toFloat(),(width*0.85).toFloat(),(height*0.78).toFloat(),p)
        p.color = Color.rgb(245,222,179)
        c.drawRect((width*0.58).toFloat(),(height*0.75).toFloat(),(width*0.65).toFloat(),(height*0.85).toFloat(),p)
        p.color = Color.rgb(218,165,32)
        c.drawCircle((width*0.63).toFloat(),(height*0.80).toFloat(),10f,p)
    }

    private fun dibujarArbol(c: Canvas){
        val p = Paint()
        p.color = Color.rgb(139,69,19)
        c.drawRect((width*0.10).toFloat(),(height*0.75).toFloat(),(width*0.17).toFloat(),(height*0.85).toFloat(),p)
        p.color = Color.GREEN
        c.drawOval((width*0.03).toFloat(),(height*0.75).toFloat(),(width*0.25).toFloat(),(height*0.70).toFloat(),p)
        c.drawOval((width*0.06).toFloat(),(height*0.70).toFloat(),(width*0.22).toFloat(),(height*0.65).toFloat(),p)
        p.color = Color.WHITE
        c.drawOval((width*0.09).toFloat(),(height*0.65).toFloat(),(width*0.19).toFloat(),(height*0.60).toFloat(),p)
    }

    private fun dibujarMontañaDerecha(c: Canvas) {
        val p = Paint()
        //Se dibuja montaña derecha
        p.color = Color.GREEN
        p.strokeWidth = 50f
        c.drawLine((width/2).toFloat(),(height/2).toFloat(),width.toFloat(),(height/2).toFloat(),p)
        c.drawLine((width*0.5).toFloat(),(height/2).toFloat(),(width*0.75).toFloat(),(height/4).toFloat(),p)
        c.drawLine((width*0.75).toFloat(),(height/4).toFloat(),width.toFloat(),(height/2).toFloat(),p)
        //Se dibuja la nieve en la montaña derecha
        p.color = Color.WHITE
        c.drawLine((width*0.59).toFloat(),(height/2.5).toFloat(),(width*0.91).toFloat(),(height/2.5).toFloat(),p)
        c.drawLine((width*0.59).toFloat(),(height/2.5).toFloat(),(width*0.75).toFloat(),(height/4).toFloat(),p)
        c.drawLine((width*0.75).toFloat(),(height/4).toFloat(),(width*0.91).toFloat(),(height/2.5).toFloat(),p)
    }

    private fun dibujarNubes(c: Canvas){
        val p = Paint()
        p.color = Color.WHITE
        c.drawOval(0f,0f,400f,100f,p)
        c.drawOval(200f,0f,800f,100f,p)
        c.drawOval(600f,0f,width.toFloat(),100f,p)
    }
    private fun dibujarMontañaIzquierda(c: Canvas){
        val p = Paint()
        //Se dibuja montaña izquierda
        p.color = Color.GREEN
        p.strokeWidth = 50f
        c.drawLine(0f,(height/2).toFloat(),(width/2).toFloat(),(height/2).toFloat(),p)
        c.drawLine((width/4).toFloat(),(height/4).toFloat(),(width/2).toFloat(),(height/2).toFloat(),p)
        c.drawLine((width/4).toFloat(),(height/4).toFloat(),0f,(height/2).toFloat(),p)
        //Se dibuja la nieve en la montaña izquierda
        p.color = Color.WHITE
        c.drawLine((width/12).toFloat(),(height/2.5).toFloat(),(width/2.3).toFloat(),(height/2.5).toFloat(),p)
        c.drawLine((width/4).toFloat(),(height/4).toFloat(),(width/2.4).toFloat(),(height/2.5).toFloat(),p)
        c.drawLine((width/4).toFloat(),(height/4).toFloat(),(width/12).toFloat(),(height/2.5).toFloat(),p)
    }

    private fun dibujarSuelo(c: Canvas){
        val p = Paint()
        p.color = Color.WHITE
        c.drawRect(0f,1500f,1080f,height.toFloat(),p)
        p.strokeWidth = 30f
        p.color = Color.BLACK
        c.drawLine((width*0.25).toFloat(),height.toFloat(),(width*0.4).toFloat(),(height*0.85).toFloat(),p)
        c.drawLine((width*0.50).toFloat(),height.toFloat(),(width*0.65).toFloat(),(height*0.85).toFloat(),p)
    }
}