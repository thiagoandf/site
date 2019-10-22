(ns caiorulli.github.io
  (:require [stasis.core :as stasis]
            [hiccup.core :refer [html]]
            [garden.core :refer [css]]
            [caiorulli.github.cleaner :as cleaner]))

(def home-page
  (html [:html
         [:head
          [:meta {:charset "utf-8"}]
          [:meta {:name    "viewport"
                  :content "width=device-width, initial-scale=1"}]
          [:link {:href "css/style.css"
                  :rel  "stylesheet"
                  :type "text/css"}]
          [:link {:rel  "icon"
                  :href "https://clojurescript.org/images/cljs-logo-icon-32.png"}]
          [:link {:rel         "stylesheet"
                  :href        "https://use.fontawesome.com/releases/v5.11.2/css/all.css"
                  :integrity   "sha384-KA6wR/X5RY4zFAHpv/CnoG2UW1uogYfdnP67Uv7eULvTveboZJg0qUpmJZb5VqzN"
                  :crossorigin "anonymous"}]
          [:link {:rel   "stylesheet"
                  :media "screen"
                  :href  "https://fontlibrary.org/face/fantasque-sans-mono"
                  :type  "text/css"}]
          [:title "caiorulli.github.io"]]
         [:body
          [:h1 "Meu objetivo é a conquista!"]
          [:iframe {:width           "560"
                    :height          "315"
                    :src             "https://www.youtube.com/embed/80SSXCgdCuU"
                    :frameborder     "0"
                    :allow           "accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                    :allowfullscreen "true"}]
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
            [:i.fab.fa-medium]]]]]))

(def text-color "#F2F2F2")
(def default-spacing "8px")

(def css-file
  (css [[:body {:margin           "0px"
                :font-family      "'FantasqueSansMonoRegular'"
                :color            text-color
                :background-color "#0B0B0B"
                :width            "100%"
                :display          "flex"
                :flex-direction   "column"
                :align-items      "center"
                :justify-content  "space-evenly"}]
        [:div.quote {:max-width        "400px"
                     :background-color "#1B1B1B"
                     :padding          "16px"
                     :border-radius    "16px"}]
        [:div.social-icons {:margin-top default-spacing
                            :padding    default-spacing}]
        [:p.quote-content {:font-style "italic"}]
        [:p.quote-author {:text-align "right"}]
        [:p.quote-author :p.quote-content {:margin "0px"}]
        [:.icon {:color     text-color
                 :margin    default-spacing
                 :font-size "32px"}]]))

(defn- get-pages []
  {"/index.html"    home-page
   "/css/style.css" css-file})

(def app
  (stasis/serve-pages get-pages))

(def export-dir "./caiorulli.github.io")

(defn- load-export-dir []
  (stasis/slurp-directory export-dir #"\.[^.]+$"))

(defn export
  "Export the entire site as flat files to the export-dir."
  []
  (let [old-files (load-export-dir)]
    (cleaner/empty-git-directory! export-dir)
    (stasis/export-pages (get-pages) export-dir)
    (println)
    (println "Export complete:")
    (stasis/report-differences old-files (load-export-dir))
    (println)))
