import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'ant-design-vue/dist/antd.css'; 
import {Button, Modal, Pagination} from 'ant-design-vue'


const app = createApp(App).use(router);
app.config.productionTip = false;
app.use(Button);
app.use(Modal);
app.use(Pagination);
app.use(router);
app.mount('#app');