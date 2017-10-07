package com.pguardiola.uigesturegen.dsl

import android.graphics.Point
import android.view.MotionEvent
import kategory.Free
import kategory.FreeMonadInstance
import kategory.higherkind


typealias ActionDSL<A> = Free<GesturesDSLHK, A>

@higherkind sealed class GesturesDSL<A> : GesturesDSLKind<A> {

    class F private constructor()

    object Click : ActionDSL<Boolean>()
    data class PinchIn(val percent: Int, val steps: Int) : ActionDSL<Boolean>()
    data class PinchOut(val percent: Int, val steps: Int) : ActionDSL<Boolean>()
    data class SwipeLeft(val steps: Int) : ActionDSL<Boolean>()
    data class SwipeRight(val steps: Int) : ActionDSL<Boolean>()
    data class SwipeUp(val steps: Int) : ActionDSL<Boolean>()
    data class SwipeDown(val steps: Int) : ActionDSL<Boolean>()
    data class MultiTouch(val touches: List<Array<MotionEvent.PointerCoords>>) : ActionDSL<Boolean>()
    data class TwoPointer(val firstStart: Point, val firstEnd: Point, val secondStart: Point, val secondEnd: Point, val steps: Int) : ActionDSL<Boolean>()

    companion object : FreeMonadInstance<GesturesDSLHK>
}

fun click(): DSLAction<Boolean> = Free.liftF(GesturesDSL.Click)
fun pinchIn(percent: Int, steps: Int): DSLAction<Boolean> = Free.liftF(GesturesDSL.PinchIn(percent, steps))
fun pinchOut(percent: Int, steps: Int): DSLAction<Boolean> = Free.liftF(GesturesDSL.PinchOut(percent, steps))
fun swipeLeft(steps: Int): ActionDSL<Boolean> = Free.liftF(GesturesDSL.SwipeLeft(steps))
fun swipeRight(steps: Int): ActionDSL<Boolean> = Free.liftF(GesturesDSL.SwipeRight(steps))
fun swipeUp(steps: Int): ActionDSL<Boolean> = Free.liftF(GesturesDSL.SwipeUp(steps))
fun swipeDown(steps: Int): ActionDSL<Boolean> = Free.liftF(GesturesDSL.SwipeDown(steps))
fun multiTouch(touches: List<Array<MotionEvent.PointerCoords>>): ActionDSL<Boolean> = Free.liftF(GesturesDSL.MultiTouch(touches))
fun twoPointer(firstStart: Point, firstEnd: Point, secondStart: Point, secondEnd: Point, steps: Int): ActionDSL<Boolean> = Free.liftF(GesturesDSL.TwoPointer(firstStart, secondStart, firstEnd, secondEnd, steps))
