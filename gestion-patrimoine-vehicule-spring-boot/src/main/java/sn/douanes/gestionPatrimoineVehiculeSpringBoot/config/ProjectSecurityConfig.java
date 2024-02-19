package sn.douanes.gestionPatrimoineVehiculeSpringBoot.config;

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
import sn.douanes.gestionPatrimoineVehiculeSpringBoot.filter.*;

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
                })).csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/error","/public")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(),BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(
                        (requests)->requests
                                // .requestMatchers("/Authorities").hasAuthority("ADMINISTRATEUR")
                                // .requestMatchers("/Users").hasAuthority("ADMINISTRATEUR")
                                // .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                                // .requestMatchers("/myLoans").authenticated()
                                // .requestMatchers("/myCards").hasRole("USER")
                                // .requestMatchers("/user").authenticated()
                                // .requestMatchers("/notices","/contact","/register").permitAll())
                                // .requestMatchers("/connexion").authenticated()
                                // .requestMatchers("/inscription").permitAll()

                                .requestMatchers("/Authorities").authenticated()
                                .requestMatchers("/Users").authenticated()
                                .requestMatchers("/connexion").authenticated()
                                .requestMatchers("/inscription").authenticated()

                                .requestMatchers("/ChangementPieces").authenticated()
                                .requestMatchers("/AjouterChangementPiece").authenticated()
                                .requestMatchers("/ModifierChangementPiece").authenticated()
                                .requestMatchers("/SupprimerChangementPieceById/*").authenticated()

                                .requestMatchers("/Controles").authenticated()
                                .requestMatchers("/AjouterControle").authenticated()
                                .requestMatchers("/ModifierControle").authenticated()
                                .requestMatchers("/SupprimerControleById/*").authenticated()

                                .requestMatchers("/CorpsAgents").authenticated()
                                .requestMatchers("/AjouterCorpsAgent").authenticated()
                                .requestMatchers("/ModifierCorpsAgent").authenticated()
                                .requestMatchers("/SupprimerCorpsAgentById/*").authenticated()

                                .requestMatchers("/DotationVehicules").authenticated()
                                .requestMatchers("/AjouterDotationVehicule").authenticated()
                                .requestMatchers("/ModifierDotationVehicule").authenticated()
                                .requestMatchers("/SupprimerDotationVehiculeById/*").authenticated()

                                .requestMatchers("/DotationVehiculeVehicules").authenticated()
                                .requestMatchers("/RecupererDotationByVehiculeId/*").authenticated()
                                .requestMatchers("/AjouterDotationVehiculeVehicule").authenticated()
                                .requestMatchers("/ModifierDotationVehiculeVehicule").authenticated()
                                .requestMatchers("/SupprimerDotationVehiculeVehiculeById/*").authenticated()

                                .requestMatchers("/EtatVehicules").authenticated()
                                .requestMatchers("/AjouterEtatVehicule").authenticated()
                                .requestMatchers("/ModifierEtatVehicule").authenticated()
                                .requestMatchers("/SupprimerEtatVehiculeById/*").authenticated()

                                .requestMatchers("/FonctionAgents").authenticated()
                                .requestMatchers("/AjouterFonctionAgent").authenticated()
                                .requestMatchers("/ModifierFonctionAgent").authenticated()
                                .requestMatchers("/SupprimerFonctionAgentById/*").authenticated()

                                .requestMatchers("/LieuStockageVehicules").authenticated()
                                .requestMatchers("/AjouterLieuStockageVehicule").authenticated()
                                .requestMatchers("/ModifierLieuStockageVehicule").authenticated()
                                .requestMatchers("/SupprimerLieuStockageVehiculeById/*").authenticated()

                                .requestMatchers("/Fournisseurs").authenticated()
                                .requestMatchers("/AjouterFournisseurs").authenticated()
                                .requestMatchers("/ModifierFournisseurs").authenticated()
                                .requestMatchers("/SupprimerFournisseursById/*").authenticated()

                                .requestMatchers("/MarqueArmes").authenticated()
                                .requestMatchers("/AjouterMarqueArme").authenticated()
                                .requestMatchers("/ModifierMarqueArme").authenticated()
                                .requestMatchers("/SupprimerMarqueArmeById/*").authenticated()

                                .requestMatchers("/Maintenances").authenticated()
                                .requestMatchers("/AjouterMaintenance").authenticated()
                                .requestMatchers("/ModifierMaintenance").authenticated()
                                .requestMatchers("/SupprimerMaintenanceById/*").authenticated()

                                .requestMatchers("/Materiels").authenticated()
                                .requestMatchers("/AjouterMateriels").authenticated()
                                .requestMatchers("/ModifierMateriels").authenticated()
                                .requestMatchers("/SupprimerMaterielsById/*").authenticated()

                                .requestMatchers("/MarqueVehicules").authenticated()
                                .requestMatchers("/AjouterMarqueVehicule").authenticated()
                                .requestMatchers("/ModifierMarqueVehicule").authenticated()
                                .requestMatchers("/SupprimerMarqueVehiculeById/*").authenticated()

                                .requestMatchers("/SecteurActivites").authenticated()
                                .requestMatchers("/AjouterSecteurActivite").authenticated()
                                .requestMatchers("/ModifierSecteurActivite").authenticated()
                                .requestMatchers("/SupprimerSecteurActiviteById/*").authenticated()

                                .requestMatchers("/PrestatairesSecteurs").authenticated()
                                .requestMatchers("/AjouterPrestatairesSecteurs").authenticated()
                                .requestMatchers("/ModifierPrestatairesSecteur").authenticated()
                                .requestMatchers("/SupprimerPrestatairesSecteurById/*").authenticated()

                                .requestMatchers("/Prestataires").authenticated()
                                .requestMatchers("/AjouterPrestataires").authenticated()
                                .requestMatchers("/ModifierPrestataires").authenticated()
                                .requestMatchers("/SupprimerPrestatairesById/*").authenticated()

                                .requestMatchers("/Pays").authenticated()
                                .requestMatchers("/AjouterPays").authenticated()
                                .requestMatchers("/ModifierPays").authenticated()
                                .requestMatchers("/SupprimerPaysById/*").authenticated()


                                .requestMatchers("/TypeEnergies").authenticated()
                                .requestMatchers("/AjouterTypeEnergie").authenticated()
                                .requestMatchers("/ModifierTypeEnergie").authenticated()
                                .requestMatchers("/SupprimerTypeEnergieById/*").authenticated()

                                .requestMatchers("/TypeArmes").authenticated()
                                .requestMatchers("/AjouterTypeArme").authenticated()
                                .requestMatchers("/ModifierTypeArme").authenticated()
                                .requestMatchers("/SupprimerTypeArmeById/*").authenticated()

                                .requestMatchers("/Tickets").authenticated()
                                .requestMatchers("/AjouterTickets").authenticated()
                                .requestMatchers("/ModifierTickets").authenticated()
                                .requestMatchers("/SupprimerTicketsById/*").authenticated()

                                .requestMatchers("/Sections").authenticated()
                                .requestMatchers("/AjouterSections").authenticated()
                                .requestMatchers("/ModifierSections").authenticated()
                                .requestMatchers("/SupprimerSectionsById/*").authenticated()

                                .requestMatchers("/TypeVehicules").authenticated()
                                .requestMatchers("/AjouterTypeVehicule").authenticated()
                                .requestMatchers("/ModifierTypeVehicule").authenticated()
                                .requestMatchers("/SupprimerTypeVehiculeById/*").authenticated()

                                .requestMatchers("/TypeUniteDouanieres").authenticated()
                                .requestMatchers("/AjouterTypeUniteDouaniere").authenticated()
                                .requestMatchers("/ModifierTypeUniteDouaniere").authenticated()
                                .requestMatchers("/SupprimerTypeUniteDouaniereById/*").authenticated()

                                .requestMatchers("/TypeObjets").authenticated()
                                .requestMatchers("/AjouterTypeObjet").authenticated()
                                .requestMatchers("/ModifierTypeObjet").authenticated()
                                .requestMatchers("/SupprimerTypeObjetById/*").authenticated()

                                .requestMatchers("/TypeMateriels").authenticated()
                                .requestMatchers("/AjouterTypeMateriel").authenticated()
                                .requestMatchers("/ModifierTypeMateriel").authenticated()
                                .requestMatchers("/SupprimerTypeMaterielById/*").authenticated()

                                .requestMatchers("/Vehicules").authenticated()
                                .requestMatchers("/AjouterVehicule").authenticated()
                                .requestMatchers("/ajouterVehiculeDotation").authenticated()
                                .requestMatchers("/ModifierVehicule").authenticated()
                                .requestMatchers("/SupprimerVehiculeById/*").authenticated()

                                .requestMatchers("/UniteHierarchiques").authenticated()
                                .requestMatchers("/AjouterUniteHierarchique").authenticated()
                                .requestMatchers("/ModifierUniteHierarchique").authenticated()
                                .requestMatchers("/SupprimerUniteHierarchiqueById/*").authenticated()

                                .requestMatchers("/UniteDouaniereSections").authenticated()
                                .requestMatchers("/AjouterUniteDouaniereSectionss").authenticated()
                                .requestMatchers("/ModifierUniteDouaniereSections").authenticated()
                                .requestMatchers("/SupprimerUniteDouaniereSectionsById/*").authenticated()

                                .requestMatchers("/UniteDouanieres").authenticated()
                                .requestMatchers("/AjouterUniteDouaniere").authenticated()
                                .requestMatchers("/ModifierUniteDouaniere").authenticated()
                                .requestMatchers("/SupprimerUniteDouaniereById/*").authenticated()
                                .requestMatchers("/RecupererUniteDouaniereById/*").authenticated()

                                .requestMatchers("/ArticleBonPours").authenticated()
                                .requestMatchers("/AjouterArticleBonPour").authenticated()
                                .requestMatchers("/ModifierArticleBonPour").authenticated()
                                .requestMatchers("/SupprimerArticleBonPourById/*/*").authenticated()
                                .requestMatchers("/RecupererArticleBonPourById/*/*").authenticated()

                                .requestMatchers("/ArticleBonEntrees").authenticated()
                                .requestMatchers("/AjouterArticleBonEntree").authenticated()
                                .requestMatchers("/ModifierArticleBonEntree").authenticated()
                                .requestMatchers("/SupprimerArticleBonEntreeById/*/*").authenticated()

                                .requestMatchers("/Armes").authenticated()
                                .requestMatchers("/AjouterArmes").authenticated()
                                .requestMatchers("/ModifierArmes").authenticated()
                                .requestMatchers("/SupprimerArmesById/*").authenticated()

                                .requestMatchers("/Agents").authenticated()
                                .requestMatchers("/AjouterAgent").authenticated()
                                .requestMatchers("/ModifierAgent").authenticated()
                                .requestMatchers("/SupprimerAgentById/*/*").authenticated()

                                .requestMatchers("/Vidanges").authenticated()
                                .requestMatchers("/AjouterVidange").authenticated()
                                .requestMatchers("/ModifierVidange").authenticated()
                                .requestMatchers("/SupprimerVidangeById/*").authenticated()

                                .requestMatchers("/Carburants").authenticated()
                                .requestMatchers("/AjouterCarburant").authenticated()
                                .requestMatchers("/ModifierCarburant").authenticated()
                                .requestMatchers("/SupprimerCarburantById/*").authenticated()

                                .requestMatchers("/BordereauLivraisons").authenticated()
                                .requestMatchers("/AjouterBordereauLivraison").authenticated()
                                .requestMatchers("/ModifierBordereauLivraison").authenticated()
                                .requestMatchers("/SupprimerBordereauLivraisonById/*").authenticated()

                                .requestMatchers("/BonPours").authenticated()
                                .requestMatchers("/AjouterBonPour").authenticated()
                                .requestMatchers("/ModifierBonPour").authenticated()
                                .requestMatchers("/SupprimerBonPourById/*").authenticated()
                                .requestMatchers("/RecupererBonPourById/*").authenticated()

                                .requestMatchers("/BonEntrees").authenticated()
                                .requestMatchers("/AjouterBonEntree").authenticated()
                                .requestMatchers("/ModifierBonEntree").authenticated()
                                .requestMatchers("/SupprimerBonEntreeById/*").authenticated()
                                .requestMatchers("/RecupererBonEntreeById/*").authenticated()

                                .requestMatchers("/BonDeSorties").authenticated()
                                .requestMatchers("/AjouterBonDeSortie").authenticated()
                                .requestMatchers("/ModifierBonDeSortie").authenticated()
                                .requestMatchers("/SupprimerBonDeSortieById/*").authenticated()
                                .requestMatchers("/RecupererBonDeSortieById/*").authenticated()

                                .requestMatchers("/ArticleBonSorties").authenticated()
                                .requestMatchers("/AjouterArticleBonSortie").authenticated()
                                .requestMatchers("/ModifierArticleBonSortie").authenticated()
                                .requestMatchers("/SupprimerArticleBonSortieById/*/*").authenticated()

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
