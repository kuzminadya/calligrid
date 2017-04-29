(ns calligrid.core
  (:require [rum.core :as rum]))

(enable-console-print!)

(defonce app-state (atom {:number-of-lines 10
                          :distance 50
                          :ascender-height 10
                          :descender-height 10
                          :x-height 20}))

(defn mousedown [e] (.log js/console "down: " (-> e .-screenX)))
(defn ondrag [e] (.log js/console "drag: " (-> e .-screenX)))

(defn line [y style]
  (let [baseline? (= style :baseline)
        color (if baseline? "black" "gray")]
    [:g
      (when baseline? [:circle {:cx 5
                                :cy y
                                :r 5
                                :on-drag #(ondrag %)
                                :on-mouse-down #(mousedown %)}])

      [:line {:x1 0 :y1 y :x2 600 :y2 y :stroke color :stroke-width 2 :key y}]]))

(defn lines [nol dist]
  (for [y (range 5 (* nol dist) dist)]
    [:g
      (line y :baseline)
      (line (- y 20) :ascender)
      (line (- y 12) :x-height)
      (line (+ y 5) :descender)]))

(defn change-number [event]
  (swap! app-state assoc :number-of-lines (.. event -target -value)))

(defn change-distance [event]
  (swap! app-state assoc :distance (js/parseInt (.. event -target -value))))

(rum/defc page < rum/reactive []
  (let [{:keys [number-of-lines distance]} (rum.core/react app-state)]
    [:div
     [:input {:type "range" :min 10 :max 30 :step 1 :on-change change-number}]
     [:input {:type "range" :min 20 :max 80 :step 1 :on-change change-distance}]
     [:h1 (str "number of lines: " number-of-lines)]
     [:svg {:width 600 :height 800}
      (lines number-of-lines distance)]]))

(rum/mount (page) (js/document.getElementById "app"))
