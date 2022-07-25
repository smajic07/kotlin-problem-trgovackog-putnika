package com.example.novaaplikacija

import android.app.Application

class GlobalnaKlasa: Application() {

    companion object {

        val grad1 = Grad("Gradačac", "18.4276","44.8785", "Bosna i Hercegovina",
                    "Gradačac je grad i naseljeno mjesto u sjeveroistočnom dijelu Bosne i Hercegovine. Nalazi " +
                    "se na obroncima Majevice, na nadmorskoj visini od 129 metara. Gradom dominira kula Husein-bega " +
                    "Gradaščevića, poznatija kao Gradina, koja je zaštitni znak i prepoznatljivi simbol grada.")

        val grad2 = Grad("Travnik", "17.6667", "44.2333", "Bosna i Hercegovina",
            "Travnik je naseljeno mjesto i sjedište istoimene općine u centralnom dijelu Bosne i Hercegovine, " +
                    "90 km zapadno od Sarajeva, te je ujedno glavni grad Srednjobosanskog kantona. U općini Travnik " +
                    "živi oko 57.000 stanovnika. Poznat je po tome što je bio prijestolnica osmanskih vezira od 1686. " +
                    "do 1850. godine.")

        val grad3 = Grad("Zenica", "17.9078","44.2039", "Bosna i Hercegovina",
                    "Zenica je grad i naseljeno mjesto u Zeničko-dobojskom kantonu, u srednjem dijelu Bosne i Hercegovine. " +
                    "Ekonomsko je središte geografske regije Srednje Bosne i pored Travnika i Bugojna najvažniji grad tog dijela države.")

        val grad4 = Grad("Kakanj", "18.1229","44.1331", "Bosna i Hercegovina",
                    "Kakanj je naseljeno mjesto i sjedište istoimene općine u krajnjem južnom dijelu Zeničko-dobojskog kantona, u " +
                    "Federaciji Bosne i Hercegovine. Nalazi se u Srednjoj Bosni, a zauzima centralni položaj u sarajevsko-zeničkoj kotlini. " +
                    "Kakanj se nalazi sjeverno od Visokog i jugoistočno od Zenice, a naseljena mjesta su uglavnom smještena u dolini " +
                    "rijeke Bosne i dolinama njenih desnih pritoka.")

        val grad5 = Grad("Sarajevo", "18.4167","43.8667", "Bosna i Hercegovina",
                    "Sarajevo je glavni i najveći grad Bosne i Hercegovine, njena metropola i njen najveći urbani, kulturni, " +
                    "ekonomski i prometni centar, glavni grad Federacije Bosne i Hercegovine i sjedište Kantona Sarajevo. " +
                    "Prema popisu stanovništva iz 1991. grad je po tadašnjoj strukturi sa deset općina imao 527.049, a po popisu " +
                    "stanovništva iz 2013. i trenutnoj teritorijalnoj podjeli sa četiri općine 275.524. Nalazi se u središnjem dijelu " +
                    "jugoistočne Evrope i Balkana.")

        val grad6 = Grad("Konjic", "17.9590","43.6536", "Bosna i Hercegovina",
                    "Konjic je grad i naseljeno mjesto na krajnjem sjeveru planinske Hercegovine, u centralnom dijelu Bosne i Hercegovine, " +
                            "u kotlini sa obje strane rijeke Neretve i oko ušća Neretvine pritoke Trešanice.")

        var gradovi : MutableList<Grad> = mutableListOf<Grad>(grad1, grad2, grad3, grad4, grad5, grad6)

    }

    override fun onCreate() {
        super.onCreate()
    }

}