(ns cozy-ui-cljs-playground.core
  (:require [reagent.core :as reagent :refer [atom]]
            ["cozy-ui/dist/react/Button" :refer [Button]]
            ["cozy-ui/dist/react/Layout" :refer [Content Layout Main]]
            ["cozy-ui/dist/react/Nav" :default Nav :refer [NavIcon NavItem NavText genNavLink]]
            ["cozy-ui/dist/react/Sidebar" :default Sidebar]))

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(def NavLink
  (genNavLink
   #(reagent/create-element "a"
                            #js {:className (.-className %)}
                            (.-children %))))

(defn hello-world []
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
    [:> Content]]])

(defn start []
  (reagent/render-component [hello-world]
                            (. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
