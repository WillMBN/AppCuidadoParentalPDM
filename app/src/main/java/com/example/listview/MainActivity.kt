package com.example.listview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sheet.view.*

class MainActivity : AppCompatActivity() {

    var charList = ArrayList<Sheet>()
    var ml:myList?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        charList.add(Sheet("Crianza severa puede alterar el desarrollo cerebral", "Una baja sensibilidad parental y una crianza severa y negativa pueden afectar el desarrollo cerebral del niño en la forma en cómo organiza su conducta", "¿Qué significa que una crianza severa pueda alterar el desarrollo cerebral? El desarrollo de las habilidades cognitivas complejas (funciones ejecutivas cerebrales) comienza dentro de los primero 5 años del niño. En paralelo, se desarrolla la corteza pre frontal del cerebro, la cual orienta la forma de organizar la propia conducta en el niño ¿Suena importante no?. Estudios han demostrado que la experiencia ambiental, como por ejemplo la parentalidad, influye en el desarrollo de esta corteza (Bumm!) y claro dentro de los primeros años del niño, éste depende única y exclusivamente de sus cuidadores. Siendo la sensibilidad del cuidador, uno de los elementos centrales de la parentalidad, definida como la capacidad de interpretar de manera adecuada las señales del niño y responder a ellas de manera atingente.  Entonces…¿Qué elementos afectan el funcionamiento cerebral del niño? Un estudio señala que la baja sensibilidad parental y una crianza severa (actos coercitivos y expresiones emocionales negativas dirigidas a los niños) están relacionadas con bajos puntajes obtenidos por el niño en la habilidad de entender la manera en que piensa y aprende (meta cognición) y controlar las respuestas impulsivas y generar respuestas mediadas por la atención y el razonamiento (auto control inhibitorio).", "Lucassen, N., Kok, R., Bakermans‐Kranenburg, M. J., Van Ijzendoorn, M. H., Jaddoe, V. W., Hofman, A., ... & Tiemeier, H. (2015). Executive functions in early childhood: The role of maternal and paternal parenting practices. British Journal of Developmental Psychology, 33(4), 489‐505.", R.drawable.ficha1))
        charList.add(Sheet("Competencia emocional", "¿Por qué es importante incorporar el lenguaje emocional? La actitud y respuesta que el cuidador tiene de las emociones del niño juega un rol importante en la manera de enseñar sobre las emociones y al mismo tiempo influencia la forma en cómo el niño expresa sus propias emociones", "Diversos estudios han demostrado que los vínculos y experiencias que tienen niños y niñas con sus familias, cuidadores, profesores y referentes cercanos, tienen influencia directa en su expresión y regulación emocional. Donde se ha revisado que aquellos niños y niñas que reciben un modelado positivo sobre expresión y manejo de emociones de sus figuras parentales, también adquieren mayor manejo y expresión apropiada de sus emociones. Además aquellas figuras parentales que presentan dificultades para identificar sus emociones (ira, tristeza) pueden tener mayores dificultades para detectar las emociones de sus hijos e hijas.", "Havighurst, S. S., Wilson, K. R., Harley, A. E., & Prior, M. R. (2009). Tuning in to kids: an emotion‐focused parenting program—initial findings from a community trial. Journal of Community Psychology, 37(8), 1008‐1023.", R.drawable.ficha2))
        charList.add(Sheet("Trauma infantil", "Estrés en los niños y funciones de la parentalidad", "Los cuidadores pueden modular las respuestas al estrés en los niños a través de la seguridad del vínculo en la relación que posean. Lo que permite controlar la respuesta fisiológica y comportamental del niño. Siendo este apoyo uno de los pilares del desarrollo de la regulación emocional. ¿A través de qué?  El vínculo de apego seguro es identificado como un mediador en las respuestas al estrés. El poder de las relaciones de apego seguras sirve para amortiguar o prevenir aumentos en el nivel de cortisol del niño. Las dinámicas familiares también influencian el desarrollo de la reactividad del cortisol en niños. En estudios de observación en ambientes naturales en niños de 2 meses a 17 años arrojan evidencia de que los eventos familiares traumáticos (conflicto, castigo, vergüenza, peleas graves y peleas) están fuertemente asociados con períodos de niveles elevados de cortisol, en comparación a sus propios niveles en días menos caóticos en la familia (Flinn e England 1995).", "Gunnar, M., & Quevedo, K. (2007). The neurobiology of stress and development. Annu. Rev. Psychol., 58, 145‐173.", R.drawable.ficha3))

        ml =  myList(this,charList)
        charListLayout.adapter = ml
    }

    inner class myList:BaseAdapter {

        var charList = ArrayList<Sheet>()
        var context:Context?=null
        constructor(context: Context, charList:ArrayList<Sheet> ) :super() {
            this.charList = charList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView:View?=null
            val sheet = this.charList[position]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            myView = inflater.inflate(R.layout.activity_sheet,null)
            myView.TV_topic.text = sheet.topic
            myView.TV_title.text = sheet.title
            myView.TV_content.text = sheet.content
            myView.TV_source.text = sheet.source
            myView.IV_image.setImageResource(sheet.img!!)

            myView.setOnClickListener {
                val intent = Intent(context, SheetData::class.java)
                intent.putExtra("topic", sheet.topic)
                intent.putExtra("title", sheet.title)
                intent.putExtra("content", sheet.content)
                intent.putExtra("source", sheet.source)
                intent.putExtra("img", sheet.img!!)
                context!!.startActivity(intent)
            }

            return myView
        }

        override fun getItem(position: Int): Any {
            return charList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return charList.size
        }
    }
}
