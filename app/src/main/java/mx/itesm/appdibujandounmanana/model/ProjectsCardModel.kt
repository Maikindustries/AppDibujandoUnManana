package mx.itesm.appdibujandounmanana.model

import java.io.Serializable

class ProjectsCardModel (val projectTitle: String,
                         val organization: String,
                         val time: String,
                         val numberWorkers: Int,
                         val money: Int,
                         val description: String,
                         val projectImage: Int): Serializable