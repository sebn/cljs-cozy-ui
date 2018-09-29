(ns cljs-cozy-ui.core
  (:require [cljs-cozy-ui.macros :refer-macros [defexample]]
            [reagent.core :as reagent]
            ["cozy-ui/dist/react/Avatar" :refer [Avatar]]
            ["cozy-ui/dist/react/Button" :refer [Button]]
            ["cozy-ui/dist/react/Hero" :default Hero :refer [Title Sections Section Icon Subtitle Paragraph CTA]]
            ["cozy-ui/dist/react/Layout" :refer [Content Layout Main]]
            ["cozy-ui/dist/react/Nav" :default Nav :refer [NavIcon NavItem NavText genNavLink]]
            ["cozy-ui/dist/react/Sidebar" :default Sidebar]))

(def NavLink
  (genNavLink
   #(reagent/create-element "a"
                            #js {:className (.-className %)}
                            (.-children %))))

(defexample hero
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
    [:> Button {:on-click #(js/console.log "Coming soon")}
     "Connect your bank accounts"]]])

(defexample button
  [:div
   (for [theme ["regular" "danger" "highlight" "secondary" "danger-outline" "alpha"]]
     [:p
      (for [props [{} {:disabled true} {:busy true}]]
        [:> Button (merge {:label theme :theme theme} props)])])])

(defexample avatar
  [:div
   [:p
    [:> Avatar {:size "small"}]
    [:> Avatar {:text "CD" :size "small"}]
    [:> Avatar {:image "https://cozy.io/fr/images/cozy-logo_white.png" :size "small"}]]
   [:p
    [:> Avatar {:size "medium"}]
    [:> Avatar {:text "CD" :size "medium"}]
    [:> Avatar {:image "https://cozy.io/fr/images/cozy-logo_white.png" :size "medium"}]]])   

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
     [hero]
     [button]
     [avatar]]]])

(defn ^:dev/after-load start []
  (reagent/render-component [demo]
                            (. js/document (getElementById "app"))))

(defn ^:export init []
  (start))
