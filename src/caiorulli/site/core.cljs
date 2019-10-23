(ns ^:figwheel-hooks caiorulli.site.core
  (:require
    [goog.dom :as gdom]
    [reagent.core :as reagent :refer [atom]]))

(defn multiply
  "Placeholder test function"
  [a b]
  (* a b))

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "Hello world!"}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn hello-world []
  [:div.wrapper
   [:h1 "Meu objetivo é a conquista!"]
   [:iframe {:width           "560"
             :height          "315"
             :src             "https://www.youtube.com/embed/80SSXCgdCuU"
             :frameBorder     "0"
             :allow           "accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
             :allowFullScreen "true"}]
   [:div.quote
    [:p.quote-content
     "\"[...] Tarde demais para isso. Não se ensinam coisas novas a um macaco velho.
     O mal é sempre lembrado, o bem é tão difícil de ser reconhecido...\""]
    [:p.quote-author
     "Dr. Gori"]]
   [:div.social-icons
    [:a.icon {:target "_blank"
              :href   "https://github.com/caiorulli"}
     [:i.fab.fa-github]]
    [:a.icon {:target "_blank"
              :href   "www.linkedin.com/in/caio-rulli-thomaz"}
     [:i.fab.fa-linkedin]]
    [:a.icon {:target "_blank"
              :href   "https://medium.com/@caiorulli"}
     [:i.fab.fa-medium]]]])

(defn mount [el]
  (reagent/render-component [hello-world] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
