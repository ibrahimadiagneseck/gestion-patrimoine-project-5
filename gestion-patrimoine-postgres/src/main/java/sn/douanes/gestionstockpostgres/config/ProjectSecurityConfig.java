package sn.douanes.gestionstockpostgres.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import sn.douanes.gestionstockpostgres.filter.*;

import java.util.Arrays;
import java.util.Collections;


@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                // config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:4201"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Arrays.asList("Authorization"));
                config.setMaxAge(3600L);
                return config;
            }
        })).csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact","/inscription")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(),BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(
                        (requests)->requests
                        .requestMatchers("/Authorities").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/ChangementPieces").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterChangementPiece").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierChangementPiece").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerChangementPieceById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Controles").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterControle").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierControle").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerControleById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/CorpsAgents").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterCorpsAgent").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierCorpsAgent").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerCorpsAgentById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/DotationVehicules").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterDotationVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierDotationVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerDotationVehiculeById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/DotationVehiculeVehicules").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/RecupererDotationByVehiculeId/*").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterDotationVehiculeVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierDotationVehiculeVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerDotationVehiculeVehiculeById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/EtatVehicules").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterEtatVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierEtatVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerEtatVehiculeById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/FonctionAgents").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterFonctionAgent").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierFonctionAgent").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerFonctionAgentById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/LieuStockageVehicules").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterLieuStockageVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierLieuStockageVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerLieuStockageVehiculeById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Fournisseurs").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterFournisseurs").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierFournisseurs").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerFournisseursById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/MarqueArmes").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterMarqueArme").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierMarqueArme").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerMarqueArmeById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Maintenances").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterMaintenance").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierMaintenance").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerMaintenanceById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Materiels").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterMateriels").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierMateriels").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerMaterielsById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/MarqueVehicules").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterMarqueVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierMarqueVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerMarqueVehiculeById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/SecteurActivites").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterSecteurActivite").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierSecteurActivite").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerSecteurActiviteById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/PrestatairesSecteurs").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterPrestatairesSecteurs").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierPrestatairesSecteur").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerPrestatairesSecteurById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Prestataires").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterPrestataires").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierPrestataires").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerPrestatairesById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Pays").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterPays").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierPays").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerPaysById/*").hasAuthority("ADMINISTRATEUR")


                        .requestMatchers("/TypeEnergies").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterTypeEnergie").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierTypeEnergie").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerTypeEnergieById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/TypeArmes").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterTypeArme").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierTypeArme").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerTypeArmeById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Tickets").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterTickets").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierTickets").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerTicketsById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Sections").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterSections").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierSections").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerSectionsById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/TypeVehicules").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterTypeVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierTypeVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerTypeVehiculeById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/TypeUniteDouanieres").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterTypeUniteDouaniere").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierTypeUniteDouaniere").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerTypeUniteDouaniereById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/TypeObjets").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterTypeObjet").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierTypeObjet").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerTypeObjetById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/TypeMateriels").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterTypeMateriel").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierTypeMateriel").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerTypeMaterielById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Vehicules").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ajouterVehiculeDotation").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierVehicule").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerVehiculeById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/UniteHierarchiques").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterUniteHierarchique").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierUniteHierarchique").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerUniteHierarchiqueById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/UniteDouaniereSections").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterUniteDouaniereSectionss").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierUniteDouaniereSections").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerUniteDouaniereSectionsById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/UniteDouanieres").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterUniteDouaniere").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierUniteDouaniere").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerUniteDouaniereById/*").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/RecupererUniteDouaniereById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/ArticleBonPours").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterArticleBonPour").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierArticleBonPour").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerArticleBonPourById/*/*").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/RecupererArticleBonPourById/*/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/ArticleBonEntrees").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterArticleBonEntree").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierArticleBonEntree").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerArticleBonEntreeById/*/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Armes").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterArmes").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierArmes").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerArmesById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Agents").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterAgent").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierAgent").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerAgentById/*/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Vidanges").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterVidange").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierVidange").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerVidangeById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Carburants").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterCarburant").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierCarburant").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerCarburantById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/BordereauLivraisons").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterBordereauLivraison").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierBordereauLivraison").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerBordereauLivraisonById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/BonPours").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterBonPour").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierBonPour").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerBonPourById/*").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/RecupererBonPourById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/BonEntrees").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterBonEntree").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierBonEntree").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerBonEntreeById/*").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/RecupererBonEntreeById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/BonDeSorties").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterBonDeSortie").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierBonDeSortie").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerBonDeSortieById/*").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/RecupererBonDeSortieById/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/ArticleBonSorties").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/AjouterArticleBonSortie").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/ModifierArticleBonSortie").hasAuthority("ADMINISTRATEUR")
                        .requestMatchers("/SupprimerArticleBonSortieById/*/*").hasAuthority("ADMINISTRATEUR")

                        .requestMatchers("/Users").hasAuthority("ADMINISTRATEUR")
                                .requestMatchers("/UniteDouanieres").hasAuthority("ADMINISTRATEUR")
                                .requestMatchers("/Sections").hasAuthority("ADMINISTRATEUR")
                                .requestMatchers("/CorpsAgents").hasAuthority("ADMINISTRATEUR")
                        // .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                        // .requestMatchers("/myLoans").authenticated()
                        // .requestMatchers("/myCards").hasRole("USER")
                        //.requestMatchers("/user").authenticated()
                        //.requestMatchers("/notices","/contact","/register").permitAll())
                        .requestMatchers("/connexion").authenticated()
                        .requestMatchers("/inscription").permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
