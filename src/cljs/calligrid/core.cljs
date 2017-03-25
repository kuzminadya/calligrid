(ns calligrid.core
  (:require [reagent.core :as reagent :refer [atom]]
            [cljsjs.snapsvg]))

(enable-console-print!)

(defonce app-state (atom {:number-of-lines 7
                          :distance 30}))

(defn greeting []
  [:div
    [:h1 (str "number of lines: " (:number-of-lines @app-state))]])

(defn draw-line [y]
  (-> (js/Snap "#svg")
      (.line 0 y 600 y)
      (.attr (clj->js {:stroke "#F00" :strokeWidth 5}))))

(defn draw []
  (let [{:keys [number-of-lines distance]} @app-state]
    (doseq [y (range 5 (* number-of-lines distance) distance)]
      (draw-line y))))


(reagent/render [greeting] (js/document.getElementById "app"))

(draw)
