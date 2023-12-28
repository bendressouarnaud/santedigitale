package com.maternite.gestion.controllers;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.maternite.gestion.beans.*;
import com.maternite.gestion.mesobjets.BeansInterventions;
import com.maternite.gestion.mesobjets.Commentaires;
import com.maternite.gestion.mesobjets.NumMedicament;
import com.maternite.gestion.mesobjets.TachesService;
import com.maternite.gestion.repositories.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ReportingController {

    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    PaiementRepository paiementRepository;
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    HopitalRepository hopitalRepository;
    @Autowired
    ProfessionRepository professionRepository;
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    ObservationRepository observationRepository;
    @Autowired
    AutorisationRepository autorisationRepository;
    @Autowired
    AffectationRepository affectationRepository;
    @Autowired
    AssignationRepository assignationRepository;
    @Autowired
    AffectationservRepository affectationservRepository;
    @Autowired
    OrdonnanceRepository ordonnanceRepository;
    @Autowired
    FichiersRepository fichiersRepository;
    @Autowired
    NaturefichierRepository naturefichierRepository;
    @Autowired
    ConstanteRepository constanteRepository;
    @Autowired
    HabilitationRepository habilitationRepository;
    @Autowired
    HeureRepository heureRepository;
    @Autowired
    RendezvousRepository rendezvousRepository;
    @Autowired
    ModedevieRepository modedevieRepository;
    @Autowired
    ServiceoptionRepository serviceoptionRepository;
    @Autowired
    QuantiteRepository quantiteRepository;
    @Autowired
    PosologieRepository posologieRepository;
    @Autowired
    DosageRepository dosageRepository;
    @Autowired
    TypesmedicRepository typesmedicRepository;
    @Autowired
    ExamenRepository examenRepository;
    @Autowired
    CmdexamensRepository cmdexamensRepository;
    @Autowired
    PharmacieRepository pharmacieRepository;
    @Autowired
    QcodesRepository qcodesRepository;
    @Autowired
    AssignationpharmRepository assignationpharmRepository;
    @Autowired
    ParametrageRepository parametrageRepository;
    @Autowired
    ProlongationRepository prolongationRepository;

    @Autowired
    JavaMailSender emailSender;
    @Autowired
    TachesService tachesService;
    @Autowired
    TamponsRepository tamponsRepository;
    @Autowired
    FactorisationrdvRepository factorisationrdvRepository;
    @Autowired
    FacturationRepository facturationRepository;
    @Autowired
    AssuranceRepository assuranceRepository;
    @Autowired
    PatientrecoursRepository patientrecoursRepository;
    @Autowired
    GroupesanguinRepository groupesanguinRepository;
    @Autowired
    HistoassuranceRepository histoassuranceRepository;
    @Autowired
    HospitalisationRepository hospitalisationRepository;
    @Autowired
    CabinetdentaireRepository cabinetdentaireRepository;
    @Autowired
    InterventionsRepository interventionsRepository;
    @Autowired
    ProthesesRepository prothesesRepository;

    //
    @Value("${cabinet.dentaire}")
    private String cabinetdentaireId;


    @PersistenceUnit
    EntityManagerFactory emf;




    // M e t h o d s :
    @GetMapping(value = "/genererdossierpat/{idpat}")
    public void genererdossierpat(@PathVariable int idpat,
        Model model, HttpSession session, Principal principal,
        HttpServletResponse response)
            throws Exception
    {
        //-------------
        response.setContentType("application/pdf");

        // Pick the PATIENT data :
        Patient patient = patientRepository.findByIdpat(idpat);
        // esperanceIcone.png

        // Now set the parameters, add an empty object as DATASOURCE :
        List<NumMedicament> listeNumMedicament = new ArrayList<>();
        NumMedicament nm = new NumMedicament();
        nm.setNum("");
        nm.setMedicament("");
        listeNumMedicament.add(nm);
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(listeNumMedicament);
        InputStream inputStream =
                this.getClass().getResourceAsStream("/reports/dmpesperance.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("numdossier", patient.getIdentifiant());
        if(patient.getDatecreation() == null){
            parameters.put("datecreation", "---");
        }
        else{
            String datecreation =
                    new SimpleDateFormat("dd-MM-yyyy").format(
                            patient.getDatecreation());
            parameters.put("datecreation", datecreation);
        }

        parameters.put("nompatient", patient.getNom());
        parameters.put("prenompatient", patient.getPrenom());
        String datenaissance =
                new SimpleDateFormat("dd-MM-yyyy").format(
                        patient.getDatenaissance());
        parameters.put("datenaissance", datenaissance);
        parameters.put("sexe", patient.getSexe());
        Profession profession =
            professionRepository.findByIdprof(patient.getProfession());
        parameters.put("profession", profession.getLibelle());
        parameters.put("contactpatient", patient.getTelephone());
        parameters.put("domicile", patient.getResidence());
        parameters.put("pere", patient.getNompere());
        parameters.put("contactpere", patient.getContactpere());
        parameters.put("mere", patient.getNommere());
        parameters.put("contactmere", patient.getContactmere());
        parameters.put("repondant", patient.getRepondant());
        parameters.put("repondantcontact", patient.getContactrepondant());
        Groupesanguin gs =
            groupesanguinRepository.findByIdgsn(patient.getGroupesanguin());
        parameters.put("groupesanguin", (gs != null ? gs.getLibelle() : ""));
        // get ASSURANCE DATA ;
        Histoassurance he = histoassuranceRepository.findTopByIdpatOrderByIdhasDesc(idpat);
        if(he!=null) {
            parameters.put("assurance", he.getAssurance());
            parameters.put("matricule", (("Matricule : ")+he.getMatricule()));
            parameters.put("sociale", he.getSociale());
            String particulier = he.getParticulier() == 0 ? "NON" : "OUI";
            parameters.put("particulier", particulier);
        }
        else {
            parameters.put("assurance", "");
            parameters.put("matricule", "Matricule : ");
            parameters.put("sociale", "");
            parameters.put("particulier", "");
        }
        parameters.put("icone", "/static/images/esperanceIcone.png");
        // Set :
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, dataSource);


        // Manage the merge :
        List<PdfReader> readers =
                new ArrayList<PdfReader>();
        //---------------- 1
        byte[] bytesUn = JasperExportManager.exportReportToPdf(jasperPrint);
        InputStream isUn = new ByteArrayInputStream(bytesUn);
        readers.add(new PdfReader(isUn));
        //-----------
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(response.getOutputStream()));
        PdfMerger merger = new PdfMerger(pdfDoc);
        // add first page :
        PdfDocument first = new PdfDocument(readers.get(0));
        merger.merge(first, 1, first.getNumberOfPages());
        first.close();

        //Consultation cn = consultationRepository.findTopByIdpat(patient.getIdpat());
        List<Consultation> lesConsultations =
                consultationRepository.findAllByIdpat(patient.getIdpat());
        boolean checkCabinetdentaire = false;

        if(principal != null) {
            // check if the USER connected is a AIDE-SOIGNANTE :
            Medecin medecin = medecinRepository.findByIdentifiant(principal.getName().trim());

            if (!medecin.getProfil().equals("aidesoignante")) {
                if (lesConsultations != null) {
                    // Browse :
                    for (Consultation cn : lesConsultations) {

                        // Check THE DATA :
                        if (Integer.valueOf(cabinetdentaireId) == cn.getIdser()) {

                            if (!checkCabinetdentaire) {

                                // This one will deny access to report if the PATIENT has a long history in CABINET DENTAIRE :
                                checkCabinetdentaire = true;

                                // Get CABINET DENTAIRE data
                                Cabinetdentaire ce = cabinetdentaireRepository.findByIdpat(idpat);
                                List<Protheses> lesProtheses = prothesesRepository.findAllByIdpat(idpat);
                                //List<Interventions> lesInterventions = interventionsRepository.findAllByIdcon(cn.getIdcon());
                                List<Interventions> lesInterventions = interventionsRepository.findAllByIdpat(idpat);
                                // Liste des beans
                                List<BeansInterventions> lInterventions = new ArrayList<BeansInterventions>();
                                BeansInterventions bs = new BeansInterventions();
                                bs.setDates("");
                                bs.setDents("");
                                bs.setInterventions("");
                                lInterventions.add(bs);
                                for (Interventions is : lesInterventions) {
                                    bs = new BeansInterventions();
                                    String dateIntervention = new SimpleDateFormat("dd/MM/yyyy").format(is.getDates());
                                    bs.setDates(dateIntervention);
                                    bs.setDents(String.valueOf(is.getDent()));
                                    bs.setInterventions(is.getNature().trim());
                                    lInterventions.add(bs);
                                }
                                //
                                JRBeanCollectionDataSource dataSourceCabinet = new JRBeanCollectionDataSource(lInterventions);
                                InputStream inputStreamCab =
                                        this.getClass().getResourceAsStream("/reports/cabinetdentaire.jrxml");
                                JasperReport jasperReportCab = JasperCompileManager.compileReport(inputStreamCab);
                                // Set the parameters :
                                Map<String, Object> parametersCab = new HashMap<>();
                                parametersCab.put("specialite", "CABINET DENTAIRE");
                                parametersCab.put("cardiopathie", ce.getCardiopathie());
                                parametersCab.put("troubleneuro", ce.getTroubles());
                                parametersCab.put("hta", ce.getHta());
                                parametersCab.put("diabete", ce.getDiabetedent());
                                parametersCab.put("asthme", ce.getAsthme());
                                parametersCab.put("orl", ce.getInfectionsorl());
                                parametersCab.put("vih", ce.getVih());
                                parametersCab.put("antibiotiques", ce.getAntibiotique());
                                parametersCab.put("anesthesiques", ce.getAnesthesie());
                                parametersCab.put("quinine", ce.getQuinine());
                                parametersCab.put("latex", ce.getLatex());
                                parametersCab.put("autres", ce.getAutresdentaire());
                                parametersCab.put("hygiene", (ce.getHygienebuccale() == 1 ? "Bonne" : "Mauvaise"));
                                parametersCab.put("articule", (ce.getArticuledentaire() == 1 ? "Correct" : "Incorrect"));
                                String langue = "";
                                if (ce.getLangue() == 1) langue = "Normale";
                                else if (ce.getLangue() == 2) langue = "Macro";
                                else if (ce.getLangue() == 3) langue = "Micro";
                                parametersCab.put("langue", langue);
                                parametersCab.put("suceur", (ce.getSuceur() == 1 ? "Suceur de pouce" : "Suceur de doigts"));
                                parametersCab.put("passeortho", (ce.getOrthodontique() == 1 ? "Oui" : "Non"));
                                parametersCab.put("dents", ce.getDentsmanquantes());
                                parametersCab.put("appareillage", (ce.getAppareillage() == 1 ? "Oui" : "Non"));
                                //-----------------------------------------------------------------------------
                                parametersCab.put("prothese1", "");
                                parametersCab.put("prothese2", "");
                                parametersCab.put("itemdatasource", dataSourceCabinet);

                                // Set :
                                JasperPrint jasperPrintCab = JasperFillManager.fillReport(jasperReportCab,
                                        parametersCab, dataSourceCabinet);
                                //---------------- 2
                                byte[] bytesDe = JasperExportManager.exportReportToPdf(jasperPrintCab);
                                InputStream isDeCab = new ByteArrayInputStream(bytesDe);

                                // add second page :
                                PdfDocument secondCab = new PdfDocument(new PdfReader(isDeCab));
                                merger.merge(secondCab, 1, secondCab.getNumberOfPages());
                                //
                                secondCab.close();
                            }
                        } else {

                            // Get the DOCTOR :
                            Medecin docteur = medecinRepository.findByIdmed(cn.getIdmed());
                            // Get SERVICE :
                            Services services = servicesRepository.findByIdser(cn.getIdser());

                            JRBeanCollectionDataSource dataSourceCons =
                                    new JRBeanCollectionDataSource(listeNumMedicament);
                            //
                            InputStream inputStreamCons =
                                    this.getClass().getResourceAsStream("/reports/ficheconsultationesp.jrxml");
                            JasperReport jasperReportCons = JasperCompileManager.compileReport(inputStreamCons);

                            // Now go to pick every CONSULTATION :
                            Map<String, Object> parametersConsul = new HashMap<>();
                            // Motif :
                            Commentaires motifComment = new Commentaires();
                            decouperTexte(cn.getMotif().toLowerCase().replaceAll("[\r\n]+", " "),
                                    motifComment, 117);
                            parametersConsul.put("motif1", motifComment.getListeCommentaire().size() >= 1 ?
                                    motifComment.getListeCommentaire().get(0) : "");
                            parametersConsul.put("motif2", motifComment.getListeCommentaire().size() >= 2 ?
                                    motifComment.getListeCommentaire().get(1).trim() : "");
                            // Antécédents :
                            Commentaires antecedentComment = new Commentaires();
                            decouperTexte(cn.getAutrescommentaire().toLowerCase().replaceAll("[\r\n]+", " "),
                                    antecedentComment, 117);
                            parametersConsul.put("antecedents1", antecedentComment.getListeCommentaire().size() >= 1 ?
                                    antecedentComment.getListeCommentaire().get(0) : "");
                            parametersConsul.put("antecedents2", antecedentComment.getListeCommentaire().size() >= 2 ?
                                    antecedentComment.getListeCommentaire().get(1).trim() : "");
                            // get Constante if possible :
                            Constante ce = constanteRepository.findByIdcon(cn.getIdcon());
                            if (ce != null) {
                                StringBuilder sr = new StringBuilder();
                                sr.append("CONSTANTES :   ");
                                sr.append("Poids : ");
                                sr.append(String.valueOf(ce.getPoids()));
                                sr.append(" Kg");
                                sr.append("  /  Température : ");
                                sr.append(String.valueOf(ce.getTemperature()));
                                sr.append(" °C");
                                sr.append("  /  Pouls : ");
                                sr.append(ce.getPouls());
                                sr.append(" bpm");
                                sr.append("  /  Tension artérielle : ");
                                sr.append(ce.getTensionarterielle());
                                sr.append(" cmHg");
                                parametersConsul.put("constantes", sr.toString());
                            } else parametersConsul.put("constantes", "CONSTANTES :   ");
                            // Examen physique :
                            Commentaires examPhys = new Commentaires();
                            decouperTexte(cn.getExamenphysique().toLowerCase().replaceAll("[\r\n]+", " "),
                                    examPhys, 117);
                            parametersConsul.put("examphysique1", examPhys.getListeCommentaire().size() >= 1 ?
                                    examPhys.getListeCommentaire().get(0) : "");
                            parametersConsul.put("examphysique2", examPhys.getListeCommentaire().size() >= 2 ?
                                    examPhys.getListeCommentaire().get(1).trim() : "");
                            parametersConsul.put("examphysique3", examPhys.getListeCommentaire().size() >= 3 ?
                                    examPhys.getListeCommentaire().get(2).trim() : "");
                            // Hypothèse diagnostic :
                            Commentaires hypoDiagnostic = new Commentaires();
                            decouperTexte(cn.getSymptome().toLowerCase().replaceAll("[\r\n]+", " "),
                                    hypoDiagnostic, 117);
                            parametersConsul.put("hypodiagn1", hypoDiagnostic.getListeCommentaire().size() >= 1 ?
                                    hypoDiagnostic.getListeCommentaire().get(0) : "");
                            parametersConsul.put("hypodiagn2", hypoDiagnostic.getListeCommentaire().size() >= 2 ?
                                    hypoDiagnostic.getListeCommentaire().get(1).trim() : "");
                            // Bilan eventuel :
                            List<Cmdexamens> listExamens = cmdexamensRepository.findAllByIdcon(cn.getIdcon());
                            StringBuilder mesExamens = new StringBuilder();
                            int cpt = 0;
                            for (Cmdexamens cs : listExamens) {
                                cpt++; // incrementer
                                Examen examen = examenRepository.findByIdexam(cs.getIdexam());
                                mesExamens.append(examen.getLibelle());
                                if (listExamens.size() == 1) mesExamens.append(" ");
                                else if (cpt < listExamens.size()) mesExamens.append(", ");
                            }

                            // Parse the EXAMENS libelle :
                            Commentaires lesexamens = new Commentaires();
                            decouperTexte(mesExamens.toString().toLowerCase(), lesexamens, 117);
                            parametersConsul.put("bilaneventuel1", lesexamens.getListeCommentaire().size() >= 1 ?
                                    lesexamens.getListeCommentaire().get(0) : "");
                            parametersConsul.put("bilaneventuel2", lesexamens.getListeCommentaire().size() >= 2 ?
                                    lesexamens.getListeCommentaire().get(1) : "");
                            // Avis :
                            Commentaires lesAvis = new Commentaires();
                            decouperTexte(cn.getAvisconfrere().toLowerCase().replaceAll("[\r\n]+", " "), lesAvis, 117);
                            parametersConsul.put("avis1", lesAvis.getListeCommentaire().size() >= 1 ?
                                    lesAvis.getListeCommentaire().get(0) : "");
                            parametersConsul.put("avis2", lesAvis.getListeCommentaire().size() >= 2 ?
                                    lesAvis.getListeCommentaire().get(1) : "");
                            // Diagnostic retenu:
                            Commentaires diagnosticRet = new Commentaires();
                            decouperTexte(cn.getDiagnosticretenu().toLowerCase().replaceAll("[\r\n]+", " "),
                                    diagnosticRet, 117);
                            parametersConsul.put("diagnosticretenu1", diagnosticRet.getListeCommentaire().size() >= 1 ?
                                    diagnosticRet.getListeCommentaire().get(0).trim() : "");
                            parametersConsul.put("diagnosticretenu2", diagnosticRet.getListeCommentaire().size() >= 2 ?
                                    diagnosticRet.getListeCommentaire().get(1).trim() : "");
                            // Ordonnance :
                            Commentaires lesOrdonn = new Commentaires();
                            decouperTexte(cn.getOrdonnance().toLowerCase().replaceAll("[\r\n]+", " "), lesOrdonn, 117);
                            parametersConsul.put("ordonnance1", lesOrdonn.getListeCommentaire().size() >= 1 ?
                                    lesOrdonn.getListeCommentaire().get(0).trim() : "");
                            parametersConsul.put("ordonnance2", lesOrdonn.getListeCommentaire().size() >= 2 ?
                                    lesOrdonn.getListeCommentaire().get(1).trim() : "");
                            parametersConsul.put("ordonnance3", lesOrdonn.getListeCommentaire().size() >= 3 ?
                                    lesOrdonn.getListeCommentaire().get(2).trim() : "");
                            // Hospitalisation :
                            parametersConsul.put("hospitalisation1", "");
                            parametersConsul.put("hospitalisation2", "");
                            // Soins:
                            Commentaires soins = new Commentaires();
                            decouperTexte(cn.getSoins().toLowerCase().replaceAll("[\r\n]+", " "),
                                    soins, 117);
                            parametersConsul.put("soins1", soins.getListeCommentaire().size() >= 1 ?
                                    soins.getListeCommentaire().get(0) : "");
                            parametersConsul.put("soins2", soins.getListeCommentaire().size() >= 2 ?
                                    soins.getListeCommentaire().get(1).trim() : "");
                            parametersConsul.put("soins3", soins.getListeCommentaire().size() >= 3 ?
                                    soins.getListeCommentaire().get(2).trim() : "");
                            String dateConsultation =
                                    new SimpleDateFormat("dd-MM-yyyy").format(
                                            cn.getDateconsultation());
                            parametersConsul.put("dates", dateConsultation);
                            // get SERVICE :
                            Services ss = servicesRepository.findByIdser(cn.getIdser());
                            parametersConsul.put("specialite", ss.getLibelle());

                            // Set :
                            JasperPrint jasperPrintCons = JasperFillManager.fillReport(jasperReportCons,
                                    parametersConsul, dataSourceCons);

                            //---------------- 2
                            byte[] bytesDe = JasperExportManager.exportReportToPdf(jasperPrintCons);
                            InputStream isDe = new ByteArrayInputStream(bytesDe);
                            //readers.add(new PdfReader(isDe));

                            // add second page :
                            PdfDocument second = new PdfDocument(new PdfReader(isDe));
                            merger.merge(second, 1, second.getNumberOfPages());
                            //
                            second.close();
                        }
                    }
                }
            }
        }

        // Generate :
        pdfDoc.close();



        /*
        HttpHeaders header = new HttpHeaders();
        final byte[] data;
        data = JasperExportManager.exportReportToPdf(jasperPrint);
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=dmpesperance.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);*/
    }





    @GetMapping(value = "/generecmcdossier/{idfac}")
    public void generecmcdossier(@PathVariable int idfac,
        Model model, HttpSession session, Principal principal,
        HttpServletResponse response)
            throws Exception
    {
        response.setContentType("application/pdf");
        // Get facturation :
        Facturation facturation = facturationRepository.findByIdfac(idfac);
        Assurance assurance =
            assuranceRepository.findByIdass(facturation.getAssurance());
        // Get Patient :
        Patient patient = patientRepository.findByIdpat(facturation.getIdpat());
        // Get Personne a contacter :
        Patientrecours ps =
            patientrecoursRepository.findTopByIdpat(patient.getIdpat());

        // Get Today's date :
        String datenaissance =
            new SimpleDateFormat("dd-MM-yyyy").format(
                    patient.getDatenaissance());

        // Now set the parameters, add an empty object as DATASOURCE :
        List<NumMedicament> listeNumMedicament = new ArrayList<>();
        NumMedicament nm = new NumMedicament();
        nm.setNum("");
        nm.setMedicament("");
        listeNumMedicament.add(nm);
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(listeNumMedicament);
        InputStream inputStream =
                this.getClass().getResourceAsStream("/reports/dossierpatientcmc.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imageclinic", "/static/images/serpentcaduce.png");
        parameters.put("nom", patient.getNom());
        parameters.put("prenom", patient.getPrenom());
        parameters.put("datenaissance", datenaissance);
        parameters.put("nompere", patient.getNompere());
        parameters.put("residence", patient.getResidence());
        parameters.put("employeur", patient.getSociete());
        parameters.put("jeunefille", patient.getNomjeunefille());
        parameters.put("sexe", patient.getSexe());
        parameters.put("lieunaissance", patient.getLieunaissance());
        parameters.put("nommere", patient.getNommere());
        parameters.put("numtelephone", patient.getTelephone());
        parameters.put("localisation", patient.getLocalisation());
        parameters.put("assurance", assurance.getLibelle());
        parameters.put("assureprincipal", "---");
        parameters.put("matricule", facturation.getNumclient());
        parameters.put("couverture", String.valueOf(facturation.getCouverture()));
        parameters.put("clientcomptant", (patient.getNom()+" "+patient.getPrenom()));
        parameters.put("nomprenom", (ps != null ? ps.getNomprenom() : ""));
        parameters.put("adressepostale", (ps != null ? ps.getAdressepostale() : ""));
        parameters.put("tel", (ps != null ? ps.getTelephone(): ""));
        parameters.put("cel", "");
        // Set :
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, dataSource);

        /*
        HttpHeaders header = new HttpHeaders();
        final byte[] data;
        data = JasperExportManager.exportReportToPdf(jasperPrint);
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=dossiermedcialcmc.pdf");
        header.setContentLength(data.length);
        */

        // Manage the merge :
        List<PdfReader> readers =
                new ArrayList<PdfReader>();
        //---------------- 1
        byte[] bytesUn = JasperExportManager.exportReportToPdf(jasperPrint);
        InputStream isUn = new ByteArrayInputStream(bytesUn);
        readers.add(new PdfReader(isUn));
        //-----------
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(response.getOutputStream()));
        PdfMerger merger = new PdfMerger(pdfDoc);
        // add first page :
        PdfDocument first = new PdfDocument(readers.get(0));
        merger.merge(first, 1, first.getNumberOfPages());
        first.close();

        //Consultation cn = consultationRepository.findTopByIdpat(patient.getIdpat());
        List<Consultation> lesConsultations =
                consultationRepository.findAllByIdpat(patient.getIdpat());
        if(lesConsultations != null) {
            // Browse :
            for(Consultation cn : lesConsultations) {

                // Get the DOCTOR :
                Medecin docteur = medecinRepository.findByIdmed(cn.getIdmed());
                // Get SERVICE :
                Services services = servicesRepository.findByIdser(cn.getIdser());

                JRBeanCollectionDataSource dataSourceCons =
                    new JRBeanCollectionDataSource(listeNumMedicament);
                //
                InputStream inputStreamCons =
                        this.getClass().getResourceAsStream("/reports/consultationcmc.jrxml");
                JasperReport jasperReportCons = JasperCompileManager.compileReport(inputStreamCons);

                // Now go to pick every CONSULTATION :
                Map<String, Object> parametersConsul = new HashMap<>();
                parametersConsul.put("imageclinic", "/static/images/serpentcaduce.png");
                String dateConsultation =
                        new SimpleDateFormat("dd-MM-yyyy").format(
                                cn.getDateconsultation());
                parametersConsul.put("dates", ("Date : "+dateConsultation));

                // Examen clinique :
                Commentaires examenClinique = new Commentaires();
                decouperTexte(cn.getExamenclinique().replaceAll("[\r\n]+", " "),
                        examenClinique);
                parametersConsul.put("examun", examenClinique.getListeCommentaire().size() >= 1 ?
                        examenClinique.getListeCommentaire().get(0) : "");
                parametersConsul.put("examdeux", examenClinique.getListeCommentaire().size() >= 2 ?
                        examenClinique.getListeCommentaire().get(1) : "");
                parametersConsul.put("examtrois", examenClinique.getListeCommentaire().size() >= 3 ?
                        examenClinique.getListeCommentaire().get(2) : "");
                parametersConsul.put("examquatre", examenClinique.getListeCommentaire().size() >= 4 ?
                        examenClinique.getListeCommentaire().get(3) : "");
                // DIAGNOSTIC
                Commentaires lediagnostic = new Commentaires();
                decouperTexte(cn.getSymptome().replaceAll("[\r\n]+", " "),
                        lediagnostic);
                parametersConsul.put("diagun",
                        lediagnostic.getListeCommentaire().size() >= 1 ?
                                lediagnostic.getListeCommentaire().get(0) : "");
                parametersConsul.put("diagdeux", lediagnostic.getListeCommentaire().size() >= 2 ?
                        lediagnostic.getListeCommentaire().get(1) : "");
                parametersConsul.put("diagtrois", lediagnostic.getListeCommentaire().size() >= 3 ?
                        lediagnostic.getListeCommentaire().get(2) : "");
                parametersConsul.put("diagquatre", lediagnostic.getListeCommentaire().size() >= 4 ?
                        lediagnostic.getListeCommentaire().get(3) : "");
                // TRAITEMENT :
                Commentaires leTraitement = new Commentaires();
                decouperTexte(cn.getTraitement().replaceAll("[\r\n]+", " "), leTraitement);
                parametersConsul.put("traitun", leTraitement.getListeCommentaire().size() >= 1 ?
                        leTraitement.getListeCommentaire().get(0) : "");
                parametersConsul.put("traitdeux", leTraitement.getListeCommentaire().size() >= 2 ?
                        leTraitement.getListeCommentaire().get(1) : "");
                parametersConsul.put("traittrois", leTraitement.getListeCommentaire().size() >= 3 ?
                        leTraitement.getListeCommentaire().get(2) : "");
                parametersConsul.put("traitquatre", leTraitement.getListeCommentaire().size() >= 4 ?
                        leTraitement.getListeCommentaire().get(3) : "");
                // Heure & autres:
                parametersConsul.put("heure", ("Heure : "+cn.getHeure()));
                parametersConsul.put("medecin", (docteur.getNom()+" "+
                        docteur.getPrenom()));
                parametersConsul.put("specialite", services.getLibelle());
                parametersConsul.put("motif", cn.getMotif());
                // Interrogatoire :
                Commentaires lInterrogatoire = new Commentaires();
                decouperTexte(cn.getInterrogatoire().replaceAll("[\r\n]+", " "), lInterrogatoire);
                parametersConsul.put("interroun", lInterrogatoire.getListeCommentaire().size() >= 1 ?
                        lInterrogatoire.getListeCommentaire().get(0) : "");
                parametersConsul.put("interrodeux", lInterrogatoire.getListeCommentaire().size() >= 2 ?
                        lInterrogatoire.getListeCommentaire().get(1) : "");
                parametersConsul.put("interrotrois", lInterrogatoire.getListeCommentaire().size() >= 3 ?
                        lInterrogatoire.getListeCommentaire().get(2) : "");
                // Examen Physique :
                Commentaires lExamenphysique = new Commentaires();
                decouperTexte(cn.getExamenphysique().replaceAll("[\r\n]+", " "), lExamenphysique);
                parametersConsul.put("examphyun", lExamenphysique.getListeCommentaire().size() >= 1 ?
                        lExamenphysique.getListeCommentaire().get(0) : "");
                parametersConsul.put("examphydeux", lExamenphysique.getListeCommentaire().size() >= 2 ?
                        lExamenphysique.getListeCommentaire().get(1) : "");
                parametersConsul.put("examphytrois", lExamenphysique.getListeCommentaire().size() >= 3 ?
                        lExamenphysique.getListeCommentaire().get(2) : "");
                // Conduite à TENIR :
                parametersConsul.put("cat", cn.getConduiteatenir());

                // get Constante if possible :
                Constante ce = constanteRepository.findByIdcon(cn.getIdcon());
                if(ce != null){
                    StringBuilder sr = new StringBuilder();
                    sr.append("Poids : ");
                    sr.append(String.valueOf(ce.getPoids()));
                    sr.append(" Kg");
                    sr.append("  /  Température : ");
                    sr.append(String.valueOf(ce.getTemperature()));
                    sr.append(" °C");
                    sr.append("  /  Tension artérielle : ");
                    sr.append(ce.getTensionarterielle());
                    sr.append(" cmHg");
                    parametersConsul.put("constantes", sr.toString());
                }
                else parametersConsul.put("constantes", "");

                // Set :
                JasperPrint jasperPrintCons = JasperFillManager.fillReport(jasperReportCons,
                        parametersConsul, dataSourceCons);

                //---------------- 2
                byte[] bytesDe = JasperExportManager.exportReportToPdf(jasperPrintCons);
                InputStream isDe = new ByteArrayInputStream(bytesDe);
                //readers.add(new PdfReader(isDe));

                // add second page :
                PdfDocument second = new PdfDocument(new PdfReader(isDe));
                merger.merge(second, 1, second.getNumberOfPages());
                //
                second.close();
            }
        }

        // Generate :
        pdfDoc.close();
    }


    @GetMapping(value = "/generecmcdossiercons/{idfac}")
    public HttpEntity<byte[]> generecmcdossiercons(@PathVariable int idfac,
                                              Model model, HttpSession session, Principal principal)
            throws Exception
    {
        // Get facturation :
        Facturation facturation = facturationRepository.findByIdfac(idfac);
        Assurance assurance =
                assuranceRepository.findByIdass(facturation.getAssurance());
        // Get Patient :
        Patient patient = patientRepository.findByIdpat(facturation.getIdpat());

        // Get Today's date :
        String datenaissance =
                new SimpleDateFormat("dd-MM-yyyy").format(
                        patient.getDatenaissance());

        // Now set the parameters, add an empty object as DATASOURCE :
        List<NumMedicament> listeNumMedicament = new ArrayList<>();
        NumMedicament nm = new NumMedicament();
        nm.setNum("");
        nm.setMedicament("");
        listeNumMedicament.add(nm);
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(listeNumMedicament);
        InputStream inputStream =
                this.getClass().getResourceAsStream("/reports/consultationcmc.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // Now go to pick every CONSULTATION :
        Map<String, Object> parametersConsul = new HashMap<>();
        parametersConsul.put("imageclinic", "/static/images/serpentcaduce.png");
        Consultation cn = consultationRepository.findTopByIdpat(patient.getIdpat());
        String dateConsultation =
                new SimpleDateFormat("dd-MM-yyyy").format(
                        cn.getDateconsultation());
        parametersConsul.put("dates", dateConsultation);

        // Examen clinique :
        Commentaires examenClinique = new Commentaires();
        decouperTexte(cn.getExamenclinique().replaceAll("[\r\n]+", " ") ,
                examenClinique);
        parametersConsul.put("examun", examenClinique.getListeCommentaire().size() >= 1 ?
                examenClinique.getListeCommentaire().get(0) : "");
        parametersConsul.put("examdeux", examenClinique.getListeCommentaire().size() >= 2 ?
                examenClinique.getListeCommentaire().get(1) : "");
        parametersConsul.put("examtrois", examenClinique.getListeCommentaire().size() >= 3 ?
                examenClinique.getListeCommentaire().get(2) : "");
        parametersConsul.put("examquatre", examenClinique.getListeCommentaire().size() >= 4 ?
                examenClinique.getListeCommentaire().get(3) : "");
        // DIAGNOSTIC
        Commentaires lediagnostic = new Commentaires();
        decouperTexte(cn.getSymptome().replaceAll("[\r\n]+", " "),
                lediagnostic);
        parametersConsul.put("diagun",
                lediagnostic.getListeCommentaire().size() >= 1 ?
                        lediagnostic.getListeCommentaire().get(0) : "" );
        parametersConsul.put("diagdeux", lediagnostic.getListeCommentaire().size() >= 2 ?
                lediagnostic.getListeCommentaire().get(1) : "");
        parametersConsul.put("diagtrois", lediagnostic.getListeCommentaire().size() >= 3 ?
                lediagnostic.getListeCommentaire().get(2) : "");
        parametersConsul.put("diagquatre", lediagnostic.getListeCommentaire().size() >= 4 ?
                lediagnostic.getListeCommentaire().get(3) : "");
        // TRAITEMENT :
        Commentaires leTraitement = new Commentaires();
        decouperTexte(cn.getTraitement().replaceAll("[\r\n]+", " "), leTraitement);
        parametersConsul.put("traitun", leTraitement.getListeCommentaire().size() >= 1 ?
                leTraitement.getListeCommentaire().get(0) : "");
        parametersConsul.put("traitdeux", leTraitement.getListeCommentaire().size() >= 2 ?
                leTraitement.getListeCommentaire().get(1) : "");
        parametersConsul.put("traittrois", leTraitement.getListeCommentaire().size() >= 3 ?
                leTraitement.getListeCommentaire().get(2) : "");
        parametersConsul.put("traitquatre", leTraitement.getListeCommentaire().size() >= 4 ?
                leTraitement.getListeCommentaire().get(3) : "");

        // Set :
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parametersConsul, dataSource);

        HttpHeaders header = new HttpHeaders();
        final byte[] data;
        data = JasperExportManager.exportReportToPdf(jasperPrint);
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=dossiermedcialcmc.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }



    public void decouperTexte(String commentaire, Commentaires liste, int... limite){

        // Set it
        int laLimite = limite.length > 0 ? limite[0] : 99;
        //*****************************
        if(commentaire.length() > laLimite){

            Commentaires cLigne = new Commentaires();
            cLigne.setCommentaire(commentaire.substring(0,laLimite-1));

            String precedent = commentaire.substring(laLimite-2,laLimite-1); //
            String suivant = commentaire.substring(laLimite-1,laLimite); //
            //int limiteCaractere = 0;
            Commentaires cLimiteCaractere = new Commentaires();
            cLimiteCaractere.setLimiteCaractere(0);
            if((!precedent.equals(" ")) && (!suivant.equals(" "))){
                retrouverEspace(laLimite-1, cLigne, cLimiteCaractere);
                cLigne.setCommentaire(commentaire.substring(0,
                        cLimiteCaractere.getLimiteCaractere()).trim());
            }

            //
            liste.getListeCommentaire().add(cLigne.getCommentaire());
            int position = cLimiteCaractere.getLimiteCaractere() > 0 ?
                    cLimiteCaractere.getLimiteCaractere() : laLimite-1;
            String commentaires = commentaire.substring(position);

            // -----------
            if(limite.length > 0) decouperTexte(commentaires, liste, limite[0] );
            else decouperTexte(commentaires, liste);
        }
        else{
            // -----------
            liste.getListeCommentaire().add(commentaire);
        }
    }

    public void retrouverEspace(int fin, Commentaires texte, Commentaires comment){
        int debut = fin - 1;
        String ligne = texte.getCommentaire().substring(debut,fin);
        //System.out.println("Ligne : "+ligne);
        if(!ligne.equals(" ")) retrouverEspace(debut, texte, comment);
        else comment.setLimiteCaractere(debut);
    }


    @GetMapping(value = "/genererbulletinesp/{idcon}")
    public HttpEntity<byte[]> genererbulletin(@PathVariable int idcon,
        Model model, HttpSession session, Principal principal)
            throws Exception
    {
        // Get Patient data :
        Consultation consultation = consultationRepository.findByIdcon(idcon);
        // Get Today's date :
        String todayDate = new SimpleDateFormat("dd-MM-yyyy").format(
            consultation.getDateconsultation());
        Patient patient = patientRepository.findByIdpat(consultation.getIdpat());
        // Get ASSURANCE, the last :
        Histoassurance he =
            histoassuranceRepository.findTopByIdpatOrderByIdhasDesc(patient.getIdpat());
        // Get EXAMENS list :
        List<Cmdexamens> listExamens = cmdexamensRepository.findAllByIdcon(idcon);
        StringBuilder mesExamens = new StringBuilder();
        int cpt = 0;
        for(Cmdexamens cs : listExamens){
            cpt++; // incrementer
            Examen examen = examenRepository.findByIdexam(cs.getIdexam());
            mesExamens.append(examen.getLibelle());
            if(listExamens.size() == 1) mesExamens.append(" ");
            else if(cpt < listExamens.size()) mesExamens.append(", ");
        }

        // Parse the EXAMENS libelle :
        Commentaires lesexamens = new Commentaires();
        decouperTexte(mesExamens.toString(), lesexamens, 44);
        //Commentaires lescliniques = new Commentaires();
        //decouperTexte(consultation.getExamenclinique(), lescliniques);
        // Parse Renseignements cliniques
        Commentaires lesRenseignements = new Commentaires();
        decouperTexte((consultation.getRenseignementsclin() != null ?
                consultation.getRenseignementsclin().replaceAll("[\r\n]+", " ") : ""),
                lesRenseignements,58);

        // Now set the parameters, add an empty object as DATASOURCE :
        List<NumMedicament> listeNumMedicament = new ArrayList<>();
        NumMedicament nm = new NumMedicament();
        nm.setNum("");
        nm.setMedicament("");
        listeNumMedicament.add(nm);
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(listeNumMedicament);
        InputStream inputStream =
                this.getClass().getResourceAsStream("/reports/bulletinexamenesp.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        /*
        dates,nom,prenom,matriculeadherent,matriculepatient,assurance,
            societe,age,sexe,exam1,exam2,rens1,rens2,imageclinic;
         */

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("icone", "/static/images/esperanceIcone.png");
        parameters.put("dates", todayDate);
        parameters.put("nom", patient.getNom());
        parameters.put("prenom", patient.getPrenom());
        if(he!=null) parameters.put("assurance", he.getAssurance());
        else parameters.put("assurance", "---");
        parameters.put("age", String.valueOf(patient.getAge()));
        parameters.put("sexe", patient.getSexe());
        parameters.put("examen1",
            lesexamens.getListeCommentaire().size() >= 1 ? lesexamens.getListeCommentaire().get(0) : "" );
        parameters.put("examen2", lesexamens.getListeCommentaire().size() >= 2 ?
                lesexamens.getListeCommentaire().get(1) : "");
        parameters.put("examen3", lesexamens.getListeCommentaire().size() >= 3 ?
                lesexamens.getListeCommentaire().get(2) : "");
        parameters.put("examen4","");
        // Renseignements :
        //System.out.println("Taille : "+lesRenseignements.getListeCommentaire().size());
        parameters.put("renseignement1",
                lesRenseignements.getListeCommentaire().size() >= 1 ? lesRenseignements.getListeCommentaire().get(0) : "" );
        parameters.put("renseignement2", lesRenseignements.getListeCommentaire().size() >= 2 ?
                lesRenseignements.getListeCommentaire().get(1).trim(): "");
        parameters.put("renseignement3", lesRenseignements.getListeCommentaire().size() >= 3 ?
                lesRenseignements.getListeCommentaire().get(2).trim() : "");
        // Set :
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, dataSource);

        HttpHeaders header = new HttpHeaders();
        final byte[] data;
        data = JasperExportManager.exportReportToPdf(jasperPrint);
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=bulletinexamen.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }


    @GetMapping(value = "/genereravishos/{idcon}")
    public HttpEntity<byte[]> genereravishos(@PathVariable int idcon,
                                              Model model, HttpSession session, Principal principal)
            throws Exception
    {
        // Get Patient data :
        Consultation consultation = consultationRepository.findByIdcon(idcon);
        // get Patient :
        Patient patient = patientRepository.findByIdpat(consultation.getIdpat());
        // Assurance :
        Histoassurance he = histoassuranceRepository.findTopByIdpatOrderByIdhasDesc(patient.getIdpat());
        // Hospitalisation
        Hospitalisation hn = hospitalisationRepository.findByIdcon(idcon);
        // Get Today's date :
        String todayDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        // Date entrée
        String dateEntree = new SimpleDateFormat("dd-MM-yyyy").format(hn.getDatentree());

        // Set the TEXTs  ---  MOTIF
        Commentaires lemotif = new Commentaires();
        decouperTexte((hn.getMotif() != null ?
            hn.getMotif().toLowerCase().replaceAll("[\r\n]+", " ") : ""), lemotif,117);
        // BIOLOGIQUES
        Commentaires biolo = new Commentaires();
        decouperTexte((hn.getBilanbio() != null ?
                hn.getBilanbio().toLowerCase().replaceAll("[\r\n]+", " ") : ""), biolo,117);
        // RADIOLOGIQUES
        Commentaires radio = new Commentaires();
        decouperTexte((hn.getBilanradio() != null ?
                hn.getBilanradio().toLowerCase().replaceAll("[\r\n]+", " ") : ""), radio,117);

        // Now set the parameters, add an empty object as DATASOURCE :
        List<NumMedicament> listeNumMedicament = new ArrayList<>();
        NumMedicament nm = new NumMedicament();
        nm.setNum("");
        nm.setMedicament("");
        listeNumMedicament.add(nm);
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(listeNumMedicament);
        InputStream inputStream =
                this.getClass().getResourceAsStream("/reports/avishospitalisation.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        /*
        dates,nom,prenom,matriculeadherent,matriculepatient,assurance,
            societe,age,sexe,exam1,exam2,rens1,rens2,imageclinic;
         */

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dates", todayDate);
        parameters.put("nom", patient.getNom());
        parameters.put("prenom", patient.getPrenom());
        parameters.put("age", String.valueOf(patient.getAge()));
        // Get the SERVICE :
        Services services = servicesRepository.findByIdser(consultation.getIdser());
        parameters.put("service", services.getLibelle());
        if(he!=null) parameters.put("societe", he.getSociete());
        else parameters.put("societe", "");
        parameters.put("chambre", hn.getChambre());
        if(he!=null) parameters.put("assurance", he.getAssurance());
        else parameters.put("assurance", "");
        parameters.put("numlit", hn.getLit());
        parameters.put("datentree", dateEntree);
        parameters.put("heure", hn.getHeure());
        //-------------------------------------
        parameters.put("motif1",
            lemotif.getListeCommentaire().size() >= 1 ? lemotif.getListeCommentaire().get(0) : "" );
        parameters.put("motif2",
                lemotif.getListeCommentaire().size() >= 2 ? lemotif.getListeCommentaire().get(1).trim() : "" );
        parameters.put("motif3",
                lemotif.getListeCommentaire().size() >= 3 ? lemotif.getListeCommentaire().get(2).trim() : "" );
        parameters.put("motif4",
                lemotif.getListeCommentaire().size() >= 4 ? lemotif.getListeCommentaire().get(3).trim() : "" );
        //-------------------------------------
        parameters.put("biologique1",
                biolo.getListeCommentaire().size() >= 1 ? biolo.getListeCommentaire().get(0) : "" );
        parameters.put("biologique2",
                biolo.getListeCommentaire().size() >= 2 ? biolo.getListeCommentaire().get(1).trim() : "" );
        //-------------------------------------
        parameters.put("radiologique1",
                radio.getListeCommentaire().size() >= 1 ? radio.getListeCommentaire().get(0) : "" );
        parameters.put("radiologique2",
                radio.getListeCommentaire().size() >= 2 ? radio.getListeCommentaire().get(1).trim() : "" );
        //-------------------------------------
        parameters.put("jours", String.valueOf(hn.getNbrejour()));
        // Get the MEDECIN
        Medecin medecin = medecinRepository.findByIdmed(consultation.getIdmed());
        parameters.put("medecin", ("Dr "+medecin.getNom()+" "+medecin.getPrenom()));
        parameters.put("icone", "/static/images/esperanceIcone.png");

        // Set :
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, dataSource);

        HttpHeaders header = new HttpHeaders();
        final byte[] data;
        data = JasperExportManager.exportReportToPdf(jasperPrint);
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=avishospitalisation.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }





    @GetMapping(value = "/genererprolongavishos/{idcon}")
    public HttpEntity<byte[]> genererprolongavishos(@PathVariable int idcon,
                                             Model model, HttpSession session, Principal principal)
            throws Exception
    {
        // Get Patient data :
        Consultation consultation = consultationRepository.findByIdcon(idcon);
        // get Patient :
        Patient patient = patientRepository.findByIdpat(consultation.getIdpat());
        // Assurance :
        Histoassurance he = histoassuranceRepository.findTopByIdpatOrderByIdhasDesc(patient.getIdpat());
        // Hospitalisation
        Hospitalisation hn = hospitalisationRepository.findByIdcon(idcon);
        // Prolongation :
        Prolongation pn = prolongationRepository.findByIdcon(idcon);

        // Get Today's date :
        String todayDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        // Date entrée
        String dateEntree = new SimpleDateFormat("dd-MM-yyyy").format(hn.getDatentree());

        // DIAGNOSTIC EVOQUE
        Commentaires lediagnoEvoque = new Commentaires();
        decouperTexte((pn.getDiagnosticevoq() != null ?
                pn.getDiagnosticevoq().toLowerCase().replaceAll("[\r\n]+", " ") : ""), lediagnoEvoque,117);
        // MOTIF DE PROLONGATION
        Commentaires motifprolong = new Commentaires();
        decouperTexte((pn.getMotif() != null ?
                pn.getMotif().toLowerCase().replaceAll("[\r\n]+", " ") : ""), motifprolong,117);
        // BIOLOGIQUES
        Commentaires biolo = new Commentaires();
        decouperTexte((pn.getBiologiques() != null ?
                pn.getBiologiques().toLowerCase().replaceAll("[\r\n]+", " ") : ""), biolo,117);
        // RADIOLOGIQUES
        Commentaires radio = new Commentaires();
        decouperTexte((pn.getRadiologiques()!= null ?
                pn.getRadiologiques().toLowerCase().replaceAll("[\r\n]+", " ") : ""), radio,117);
        // SUSPICION
        Commentaires suspicion = new Commentaires();
        decouperTexte((pn.getSuspicion()!= null ?
                pn.getSuspicion().toLowerCase().replaceAll("[\r\n]+", " ") : ""), suspicion,117);

        // Now set the parameters, add an empty object as DATASOURCE :
        List<NumMedicament> listeNumMedicament = new ArrayList<>();
        NumMedicament nm = new NumMedicament();
        nm.setNum("");
        nm.setMedicament("");
        listeNumMedicament.add(nm);
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(listeNumMedicament);
        InputStream inputStream =
                this.getClass().getResourceAsStream("/reports/avisprolongationhospi.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        // set the parameters :
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dates", todayDate);
        parameters.put("nom", patient.getNom());
        parameters.put("prenom", patient.getPrenom());
        parameters.put("age", String.valueOf(patient.getAge()));
        // Get the SERVICE :
        Services services = servicesRepository.findByIdser(consultation.getIdser());
        parameters.put("service", services.getLibelle());
        if(he!=null) parameters.put("societe", he.getSociete());
        else parameters.put("societe", "");
        parameters.put("chambre", hn.getChambre());
        if(he!=null) parameters.put("assurance", he.getAssurance());
        else parameters.put("assurance", "");
        parameters.put("numlit", hn.getLit());
        parameters.put("datentree", dateEntree);
        //parameters.put("heure", hn.getHeure());
        //-------------------------------------
        parameters.put("motif1",
                motifprolong.getListeCommentaire().size() >= 1 ? motifprolong.getListeCommentaire().get(0) : "" );
        parameters.put("motif2",
                motifprolong.getListeCommentaire().size() >= 2 ? motifprolong.getListeCommentaire().get(1).trim() : "" );
        parameters.put("motif3",
                motifprolong.getListeCommentaire().size() >= 3 ? motifprolong.getListeCommentaire().get(2).trim() : "" );
        parameters.put("motif4",
                motifprolong.getListeCommentaire().size() >= 4 ? motifprolong.getListeCommentaire().get(3).trim() : "" );
        //-------------------------------------
        parameters.put("biologique1",
                biolo.getListeCommentaire().size() >= 1 ? biolo.getListeCommentaire().get(0) : "" );
        parameters.put("biologique2",
                biolo.getListeCommentaire().size() >= 2 ? biolo.getListeCommentaire().get(1).trim() : "" );
        //-------------------------------------
        parameters.put("radiologique1",
                radio.getListeCommentaire().size() >= 1 ? radio.getListeCommentaire().get(0) : "" );
        parameters.put("radiologique2",
                radio.getListeCommentaire().size() >= 2 ? radio.getListeCommentaire().get(1).trim() : "" );
        //-------------------------------------
        parameters.put("jours", String.valueOf(pn.getJour()));
        // Get the MEDECIN
        Medecin medecin = medecinRepository.findByIdmed(consultation.getIdmed());
        parameters.put("medecin", ("Dr "+medecin.getNom()+" "+medecin.getPrenom()));
        parameters.put("icone", "/static/images/esperanceIcone.png");
        //-------------------------------------
        parameters.put("suspicion1",
                suspicion.getListeCommentaire().size() >= 1 ? suspicion.getListeCommentaire().get(0) : "" );
        parameters.put("suspicion2",
                suspicion.getListeCommentaire().size() >= 2 ? suspicion.getListeCommentaire().get(1).trim() : "" );
        parameters.put("diagnosticev1",
                lediagnoEvoque.getListeCommentaire().size() >= 1 ? lediagnoEvoque.getListeCommentaire().get(0) : "" );
        parameters.put("diagnosticev2",
                lediagnoEvoque.getListeCommentaire().size() >= 2 ? lediagnoEvoque.getListeCommentaire().get(1).trim() : "" );
        //-------------------------------------

        // Set :
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                parameters, dataSource);

        HttpHeaders header = new HttpHeaders();
        final byte[] data;
        data = JasperExportManager.exportReportToPdf(jasperPrint);
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=avisprolongationhospi.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }

}
