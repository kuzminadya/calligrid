(ns calligrid.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce app-state (atom {:number-of-lines 7
                          :distance 30}))

(defn line [y]
  [:line {:x1 0 :y1 y :x2 600 :y2 y :stroke "red" :stroke-width 5 :key y}])

(defn lines [nol dist]
  (for [y (range 5 (* nol dist) dist)]
    (line y)))

(defn page []
  (let [{:keys [number-of-lines distance]} @app-state]
    [:div
     [:h1 (str "number of lines: " number-of-lines)]
     [:svg {:width 600 :height 800}
      (lines number-of-lines distance)]]))

(reagent/render [page] (js/document.getElementById "app"))

