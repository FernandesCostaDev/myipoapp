package com.example.myipoapp.main.view.main.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.graphics.text.LineBreaker
import android.os.Bundle
import android.provider.MediaStore
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import androidx.core.graphics.withTranslation
import androidx.core.util.lruCache
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivitySummaryBinding
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale

class SummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySummaryBinding
    private var pdfDocument = PdfDocument()
    var returnFireman = ""
    var returnCrbm = ""
    var returnObm = ""
    var returnDate = ""
    var returnTime = ""
    var returnNature = ""
    var returnSubNature = ""
    var returnCity = ""
    var returnStreet = ""
    var returnNeighborhood = ""
    var returnComplement = ""
    var returnCb = ""
    var returnNumber = ""
    var returnVehicles = ""
    var returnUnharmed = 0
    var returnCode1 = 0
    var returnCode2 = 0
    var returnCode3 = 0
    var returnCode4 = 0
    var returnTotal = 0
    var returnObsVictims = ""
    var returnEnvironment = ""
    var returnProperty = ""
    var returnScenery = ""
    var returnUnfolding = ""
    var returnListSupport = ""
    var regionalCommand = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.summaryToolbar
        setSupportActionBar(toolbar)
        toolbar.overflowIcon?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.brasao_bombeiro_p)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        returnFireman = intent.getStringExtra("fireman").toString()
        returnCrbm = intent.getStringExtra("crbm").toString()
        returnObm = intent.getStringExtra("obm").toString()
        returnDate = intent.getStringExtra("date").toString()
        returnTime = intent.getStringExtra("time").toString()
        returnNature = intent.getStringExtra("nature").toString()
        returnSubNature = intent.getStringExtra("subNature").toString()
        returnCity = intent.getStringExtra("city").toString()
        returnStreet = intent.getStringExtra("street").toString()
        returnNeighborhood = intent.getStringExtra("neighborhood").toString()
        returnComplement = intent.getStringExtra("complement").toString()
        returnCb = intent.getStringExtra("cb").toString()
        returnNumber = intent.getStringExtra("number").toString()
        returnVehicles = intent.getStringExtra("vehicles").toString()
        returnUnharmed = intent.getIntExtra("unharmed", 0)
        returnCode1 = intent.getIntExtra("code1", 0)
        returnCode2 = intent.getIntExtra("code2", 0)
        returnCode3 = intent.getIntExtra("code3", 0)
        returnCode4 = intent.getIntExtra("code4", 0)
        returnTotal = intent.getIntExtra("total", 0)
        returnObsVictims = intent.getStringExtra("obsVictims").toString()
        returnEnvironment = intent.getStringExtra("environment").toString()
        returnProperty = intent.getStringExtra("property").toString()
        returnScenery = intent.getStringExtra("scenery").toString()
        returnUnfolding = intent.getStringExtra("unfolding").toString()
        returnListSupport = intent.getStringExtra("listSupport").toString()

        with(binding) {
            summaryTxtFireman.text = returnFireman
            summaryTxtCrbm.text = returnCrbm
            summaryTxtObm.text = returnObm
            summaryTxtDate.text = returnDate
            summaryTxtTime.text = returnTime
            summaryTxtNature.text = returnNature
            summaryTxtSubNature.text = returnSubNature
            summaryTxtCity.text = returnCity
            summaryTxtStreet.text = returnStreet
            summaryTxtNeighborhood.text = returnNeighborhood
            summaryTxtComplement.text = returnComplement
            summaryTxtCb.text = returnCb
            summaryTxtNumbersBms.text = returnNumber
            summaryTxtVehicles.text = returnVehicles
            summaryTxtUnharmed.text = returnUnharmed.toString()
            summaryTxtCode1.text = returnCode1.toString()
            summaryTxtCode2.text = returnCode2.toString()
            summaryTxtCode3.text = returnCode3.toString()
            summaryTxtCode4.text = returnCode4.toString()
            summaryTxtTotal.text = returnTotal.toString()
            summaryTxtObs.text = returnObsVictims
            summaryTxtEnvironment.text = returnEnvironment
            summaryTxtProperty.text = returnProperty
            summaryTxtScenery.text = returnScenery
            summaryTxtUnfolding.text = returnUnfolding
            summaryTxtSupport.text = returnListSupport


            summaryScrollView.viewTreeObserver.addOnScrollChangedListener {
                if (!summaryScrollView.canScrollVertically(1)) {
                    summaryButtonNext.isEnabled = true
                }
            }

            summaryButtonNext.setOnClickListener {
                regionalCommand = when (returnCrbm) {
                    "1º CRBM Curitiba" -> {
                        getString(R.string.crbm1)
                    }

                    "2º CRBM Londrina" -> {
                        getString(R.string.crbm2)
                    }

                    else -> {
                        getString(R.string.crbm3)
                    }
                }

                generatePdf()
                finish()

            }

        }
    }

    private fun generatePdf() {
        val pageWidth = 595
        val pageHheight = 842
        val fontSize = 9

        val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHheight, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.bombeiro_small)
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.parana_small)

        val scaledLogoBitmap1 = Bitmap.createScaledBitmap(bitmap1, 140, 37, true)
        val scaledLogoBitmap2 = Bitmap.createScaledBitmap(bitmap2, 140, 37, true)

        canvas.drawBitmap(scaledLogoBitmap1, 35f, 40f, null)
        canvas.drawBitmap(scaledLogoBitmap2, 415f, 35f, null)

        val label1 = Paint()
        label1.textSize = fontSize.toFloat()
        label1.textAlign = Paint.Align.CENTER
        label1.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        canvas.drawText(regionalCommand, 300f, 90f, label1)
        canvas.drawText(returnObm, 300f, 105f, label1)

        val label2 = Paint()
        label2.textSize = fontSize.toFloat()
        label2.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        label2.color = getColor(R.color.red)
        canvas.drawText("Para uso exclusivo no grupos CBMPR!", 40f, 125f, label2)

        canvas.drawText("Informações iniciais", 300f, 140f, label1)

        val label3 = Paint()
        label3.textSize = fontSize.toFloat()
        canvas.drawText("Responsável pela informação:", 40f, 155f, label3)
        canvas.drawText(returnFireman, 162f, 155f, label3)

        val label4 = Paint()
        label4.textSize = fontSize.toFloat()

        label1.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        canvas.drawText(regionalCommand, 300f, 90f, label1)
        canvas.drawText(returnObm, 300f, 105f, label1)

        canvas.drawText("Data:", 40f, 170f, label3)
        canvas.drawText(returnDate, 64f, 170f, label3)

        canvas.drawText("Horas:", 40f, 185f, label3)
        canvas.drawText(returnTime, 67f, 185f, label3)

        canvas.drawText("Natureza do evento:", 40f, 200f, label3)
        canvas.drawText(returnNature, 123f, 200f, label3)

        canvas.drawText("Sub Natureza:", 40f, 215f, label3)
        canvas.drawText(returnSubNature, 98f, 215f, label3)

        canvas.drawText("Endereço", 300f, 230f, label1)

        canvas.drawText("Cidade:", 40f, 245f, label3)
        canvas.drawText(returnCity, 72f, 245f, label3)

        canvas.drawText("Logradouro:", 40f, 260f, label3)
        canvas.drawText(returnStreet, 90f, 260f, label3)

        canvas.drawText("Bairro:", 40f, 275f, label3)
        canvas.drawText(returnNeighborhood, 69f, 275f, label3)

        canvas.drawText("Complemento:", 40f, 290f, label3)
        canvas.drawText(returnComplement, 102f, 290f, label3)

        canvas.drawText("Recursos", 300f, 305f, label1)

        canvas.drawText("Unidade Acionada:", 40f, 320f, label3)
        canvas.drawText(returnCb, 122f, 320f, label3)

        canvas.drawText("Efetivo, nº BMs:", 40f, 335f, label3)
        canvas.drawText(returnNumber, 110f, 335f, label3)

        canvas.drawText("Viaturas Empenhadas:", 40f, 350f, label3)
        canvas.drawText(returnVehicles, 135f, 350f, label3)

        canvas.drawText("Vítimas", 300f, 365f, label1)

        canvas.drawText("Total de vítimas:", 40f, 380f, label3)
        canvas.drawText(returnTotal.toString(), 105f, 380f, label3)

        canvas.drawText("Vítima ilesa:", 40f, 395f, label3)
        canvas.drawText(returnUnharmed.toString(), 90f, 395f, label3)

        canvas.drawText("código 1:", 115f, 395f, label3)
        canvas.drawText(returnCode1.toString(), 155f, 395f, label3)

        canvas.drawText("código 2:", 180f, 395f, label3)
        canvas.drawText(returnCode2.toString(), 218f, 395f, label3)

        canvas.drawText("código 3:", 245f, 395f, label3)
        canvas.drawText(returnCode3.toString(), 285f, 395f, label3)

        canvas.drawText("código 4:", 310f, 395f, label3)
        canvas.drawText(returnCode4.toString(), 350f, 395f, label3)

        canvas.drawText("Observações das vítimas:", 40f, 416f, label4)

        val textPaint = TextPaint()
        textPaint.textSize = fontSize.toFloat()
        canvas.drawMultiLineText("$returnObsVictims", textPaint, 510, 40F, 420F, 0)

        canvas.drawText("Danos ao meio ambiente", 300f, 470f, label1)
        canvas.drawMultiLineText(returnEnvironment, textPaint, 510, 40F, 475F, 0)

        canvas.drawText("Danos à propriedade", 300f, 535f, label1)
        canvas.drawMultiLineText(returnProperty, textPaint, 510, 40F, 540F, 0)

        canvas.drawText("Cenário", 300f, 595f, label1)
        canvas.drawMultiLineText(returnScenery, textPaint, 510, 40F, 600F, 0)

        canvas.drawText("Desdobramento", 300f, 655f, label1)
        canvas.drawMultiLineText(returnUnfolding, textPaint, 510, 40F, 660F, 0)

        canvas.drawText("Apoio", 300f, 780f, label1)
        canvas.drawMultiLineText(returnListSupport, textPaint, 510, 40F, 785F, 0)

        pdfDocument.finishPage(page)
        salvarPdf()
    }

    private fun salvarPdf() {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH-mm", Locale.getDefault())
        val fileName = "${dateFormat.format(System.currentTimeMillis())} IPO.pdf"

        val resolver = contentResolver
        val contentValues = contentValuesOf(
            MediaStore.MediaColumns.DISPLAY_NAME to fileName,
            MediaStore.MediaColumns.MIME_TYPE to "application/pdf",
            MediaStore.MediaColumns.RELATIVE_PATH to "Documents/"
        )

        val uri = resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)

        uri?.let {
            try {
                val outputStream: OutputStream? = resolver.openOutputStream(it)
                pdfDocument.writeTo(outputStream)
                outputStream?.close()
                Toast.makeText(
                    this,
                    "Arquivo salvo na pasta de documentos. \uD83D\uDE00",
                    Toast.LENGTH_LONG
                )
                    .show()

                //Abrir o arquivo salvo
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(it, "application/pdf")
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_GRANT_READ_URI_PERMISSION
                startActivity(intent)

            } catch (e: IOException) {
                Toast.makeText(this, "Erro ao salvar o arquivo. \uD83D\uDE21", Toast.LENGTH_LONG)
                    .show()
            }
        }
        pdfDocument.close()
    }

    private fun Canvas.drawMultiLineText(
        texto: String,
        textPaint: TextPaint,
        largura: Int,
        x: Float,
        y: Float,
        inicio: Int = 0,
        fim: Int = texto.length,
        alinhamento: Layout.Alignment = Layout.Alignment.ALIGN_NORMAL,
        justificaMode: Int = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
    ) {
        val cacheKey = "$texto-$inicio-$fim-$textPaint-$largura-$alinhamento-$justificaMode"
        val staticLayout = StaticLayoutCache[cacheKey] ?: StaticLayout.Builder.obtain(
            texto,
            inicio,
            fim,
            textPaint,
            largura
        )
            .setAlignment(alinhamento)
            .setJustificationMode(justificaMode)
            .build().apply { StaticLayoutCache[cacheKey] = this }
        staticLayout.draw(this, x, y)
    }
}

private fun StaticLayout.draw(canvas: Canvas, x: Float, y: Float) {
    canvas.withTranslation(x, y) {
        draw(this)
    }
}

private object StaticLayoutCache {
    private const val MAX_SIZE = 50 // Arbitrary max number of cached items

    private val cache = lruCache<String, StaticLayout>(MAX_SIZE)
    operator fun set(key: String, staticLayout: StaticLayout) {
        cache.put(key, staticLayout)
    }

    operator fun get(key: String): StaticLayout? {
        return cache[key]
    }
}
