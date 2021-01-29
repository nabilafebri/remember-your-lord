package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.gle

import android.content.Context
import android.opengl.GLSurfaceView
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.gle.GLES3JNILib.init
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.gle.GLES3JNILib.resize
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.gle.GLES3JNILib.step
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class GLES3JNIView(context: Context?) : GLSurfaceView(context) {
    private class Renderer : GLSurfaceView.Renderer {
        override fun onDrawFrame(gl: GL10) {
            step()
        }

        override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
            resize(width, height)
        }

        override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
            init()
        }
    }

    companion object {
        private const val TAG = "GLES3JNI"
        private const val DEBUG = true
    }

    init {
        // Pick an EGLConfig with RGB8 color, 16-bit depth, no stencil,
        // supporting OpenGL ES 2.0 or later backwards-compatible versions.
        setEGLConfigChooser(8, 8, 8, 0, 16, 0)
        setEGLContextClientVersion(3)
        setRenderer(Renderer())
    }
}