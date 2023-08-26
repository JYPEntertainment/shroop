import { createRouter, createWebHistory } from "vue-router";
import DefaultLayoutView from "@/layouts/default/DefaultLayout.vue";
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import SignupView from "@/views/SignupView.vue";
import MypageView from "@/views/MypageView.vue";
import PhoneAuthView from "@/views/PhoneAuthView.vue";
import RegistProductView from "@/views/RegistProductView.vue";
import ProductDetailsView from "@/views/ProductDetailsView.vue";
import ReportView from "@/views/ReportView.vue";
import ReturnRequestView from "@/views/ReturnRequestView.vue";
import EditProileView from "@/views/EditProileView.vue";
import ProductsView from "@/views/ProductsView.vue";
import PurchaseRequestView from "@/views/PurchaseRequestView.vue";
import DeliveryRegistView from "@/views/DeliveryRegistView.vue";
import PurchaseCompleteView from "@/views/PurchaseCompleteView.vue";

const routes = [
  {
    path: "/",
    component: DefaultLayoutView,
    children: [
      {
        path: "",
        name: "Home",
        component: HomeView,
      },
      {
        path: "/login",
        name: "Login",
        component: LoginView,
      },
      {
        path: "/signup",
        name: "Signup",
        component: SignupView,
      },
      {
        path: "/mypage/:index",
        props: (route) => {
          /^[0-2]$/.test(route.params.param) ? route.params.param : "0";
        },
        name: "Mypage",
        component: MypageView,
      },
      {
        path: "/regist",
        name: "Regist",
        component: RegistProductView,
      },
      {
        path: "/edit/:id(\\d+)",
        name: "Edit",
        component: RegistProductView,
      },
      {
        path: "/detail/:id(\\d+)",
        name: "Detail",
        component: ProductDetailsView,
      },
      {
        path: "/report/:id(\\d+)",
        name: "Report",
        component: ReportView,
      },
      {
        path: "/return/:id(\\d+)",
        name: "Return",
        component: ReturnRequestView,
      },
      {
        path: "/profileEdit/:id(\\d+)",
        name: "ProfileEdit",
        component: EditProileView,
      },
      {
        path: "/products",
        name: "Products",
        component: ProductsView,
      },
      {
        path: "/purchase/:id(\\d+)",
        name: "Purchase",
        component: PurchaseRequestView,
      },
      {
        path: "/purchaseComplete/:id(\\d+)",
        name: "PurchaseComplete",
        component: PurchaseCompleteView,
      },
      {
        path: "/deliveryRegist/:id(\\d+)",
        name: "DeliveryRegist",
        component: DeliveryRegistView,
      },
      {
        path: "/phone/:id",
        name: "PhoneAuth",
        component: PhoneAuthView,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  scrollBehavior() {
    return { top: 0 };
  },
});

export default router;
