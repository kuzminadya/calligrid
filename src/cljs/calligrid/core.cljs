(ns calligrid.core
  (:require [reagent.core :as reagent :refer [atom]]
            [cljsjs.snapsvg]))

(enable-console-print!)

(defonce app-state (atom {:text "Calligrid!"}))

(defn greeting []
  [:div
    [:h1 (:text @app-state)]
    [:svg#svg {:width 800 :height 600}]])


(reagent/render [greeting] (js/document.getElementById "app"))
