(ns cljs-cozy-ui.core
  (:require [reagent.core :as reagent]
            ["cozy-ui/transpiled/react" :refer [IconSprite]]
            ["cozy-ui/transpiled/react/Avatar" :refer [Avatar]]
            ["cozy-ui/transpiled/react/Button" :refer [Button]]
            ["cozy-ui/transpiled/react/Hero" :default Hero :refer [Title Sections Section Icon Subtitle Paragraph CTA]]
            ["cozy-ui/transpiled/react/Layout" :refer [Content Layout Main]]
            ["cozy-ui/transpiled/react/Nav" :default Nav :refer [NavIcon NavItem NavText genNavLink]]
            ["cozy-ui/transpiled/react/Sidebar" :default Sidebar]))

(def NavLink
  (genNavLink
   #(reagent/create-element "a"
                            #js {:className (.-className %)}
                            (.-children %))))

(defn demo []
  [:> Layout
   [:> Sidebar
    [:> Nav
     [:> NavItem
      [:> NavLink
       [:> NavIcon {:icon "warn"}]
       [:> NavText "Warn"]]]
     [:> NavItem
      [:> NavLink
       [:> NavIcon {:icon "check"}]
       [:> NavText "Check"]]]
     [:> NavItem
      [:> NavLink
       [:> NavIcon {:icon "download"}]
       [:> NavText "Download"]]]]]
   [:> Main
    [:> Content
     [:h2 "Hero"]
     [:> Hero
      [:> Title "Connect your bank accounts"]
      [:> Sections
       [:> Section
        [:> Icon {:color "#f52d2d" :icon "warning"}]
        [:> Subtitle "Control your budget"]
        [:> Paragraph "Summary of all your accounts at a glance"]]
       [:> Section
        [:> Icon {:color "#a75bcb" :icon "paperplane"}]
        [:> Subtitle "Save time"]
        [:> Paragraph "Your invoices at your fingertips directly from your statements"]]
       [:> Section
        [:> Icon {:color "2d8af2" :icon "folder"}]
        [:> Subtitle "Cozy is working for you"]
        [:> Paragraph "Automatic follow-up of your medical expenses"]]]
      [:> CTA
       [:> Button {:label "Connect your bank accounts"
                   :on-click #(js/console.log "Coming soon")}]]]
     [:h2 "Button"]
     [:div
      (for [theme ["regular" "danger" "highlight" "secondary" "danger-outline" "alpha"]]
        ^{:key theme}
        [:div
         (for [props [{} {:disabled true} {:busy true}]]
           ^{:key (str theme (-> props keys first))}
           [:> Button (merge {:label theme :theme theme} props)])])]
     [:h2 "Avatar"]
     [:div
      [:> Avatar {:size "small"}]
      [:> Avatar {:text "CD" :size "small"}]
      [:> Avatar {:image "https://cozy.io/fr/images/cozy-logo_white.png" :size "small"}]]
     [:div
      [:> Avatar {:size "medium"}]
      [:> Avatar {:text "CD" :size "medium"}]
      [:> Avatar {:image "https://cozy.io/fr/images/cozy-logo_white.png" :size "medium"}]]]]
   [IconSprite]])

(defn ^:dev/after-load start []
  (reagent/render-component [demo]
                            (. js/document (getElementById "app"))))

(defn ^:export init []
  (start))
