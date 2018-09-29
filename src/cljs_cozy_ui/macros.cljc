(ns cljs-cozy-ui.macros)

(defmacro defexample [name body]
  (list 'defn name []
    [:div
     [:div
      [:h2 (str name)]
      body]
     [:pre
      (str body)]])) 
