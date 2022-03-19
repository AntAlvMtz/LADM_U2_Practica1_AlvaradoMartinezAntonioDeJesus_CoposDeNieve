package mx.edu.ittepic.ladm_u2_practica1_alvaradomartinezantoniodejesus_coposdenieve

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.random.Random

class Copo(l:Lienzo,radio:Float) {
    val l = l
    var radio = 0f
    var x = 0f
    var y = 0f
    var movX = 0f
    var movY = 0f
    init {
        x = Random.nextInt(1000).toFloat()
        y = Random.nextInt(1950).toFloat()
        this.radio = radio
        if (this.radio==40f){
            movY = 6f
            movX = 3f
        }else{
            movY=3f
            movX = 1.5f
        }
    }
    fun caerSinViento(){
        y += movY
        if(y>=l.height){
            y=0f
        }
    }
    fun caerConViento(){
        y += movY
        x += movX
        if(y>=l.height){
            y=0f
        }else if(x>=l.width){
            x=0f
        }
    }
    fun caerFuerte(){
        y += movY*2
        if(y>=l.height){
            y=0f
        }
    }

    fun pintar(canvas: Canvas){
        val p = Paint()
        p.color = Color.WHITE
        canvas.drawCircle(x,y,radio,p)
    }
}