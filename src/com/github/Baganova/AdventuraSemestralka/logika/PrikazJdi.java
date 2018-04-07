package com.github.Baganova.AdventuraSemestralka.logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];
        
        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        Batoh pomocny = plan.getBatoh();
        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else 
        {
                if(smer.equals("Prales"))
                {
                    if(pomocny.obsahujeVec("mačeta") && pomocny.obsahujeVec("mapa"))
                    {
                        plan.getHra().setKonecHry(true);
                        return "Úspěšně jste vstoupili do pralesa - výhra";
                    }
                    else
                    {
                        plan.getHra().setKonecHry(true);
                        return "Bez mapy a mačety jsi v Pralese umřel - konec hry";
                    }
                }
                if(smer.equals("Studna"))
                {
                   plan.getHra().setKonecHry(true);
                   return "Spadl jsi do studny - konec hry - prohra";
                }
                if(smer.equals("Loď"))
                {
                   plan.getHra().setKonecHry(true);
                   return "Máš jít do Pralesa, né zbaběle utéct - Konec hry - Prohra"; 
                }
                plan.setAktualniProstor(sousedniProstor);
                return sousedniProstor.dlouhyPopis();   
          
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}