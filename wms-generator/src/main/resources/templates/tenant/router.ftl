import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
 **/
export const constantRouterMap = [
  {path: '/login', component: () => import('@/views/login/index'), hidden: true},
  {path: '/404', component: () => import('@/views/404'), hidden: true},
  {
    path: '/${projectName}',
    component: Layout,
    redirect: '/${projectName}/${table.entityName?uncap_first}',
    name: '${projectName}',
    meta: {title: '${table.tableComment}', icon: '${table.entityName?uncap_first}'},
    children: [{
      path: '${table.entityName?uncap_first}',
      name: '${table.entityName?uncap_first}',
      component: () => import('@/views/${projectName}/${table.entityName?uncap_first}/index'),
      meta: {title: '${table.tableComment}列表', icon: '${table.entityName?uncap_first}-list'}
    },
      {
        path: 'add${table.entityName?cap_first}',
        name: 'add${table.entityName?cap_first}',
        component: () => import('@/views/${projectName}/${table.entityName?uncap_first}/add'),
        meta: {title: '添加${table.tableComment}', icon: '${table.entityName?uncap_first}-add'},
        hidden: true
      },
      {
        path: 'view${table.entityName?cap_first}',
        name: 'view${table.entityName?cap_first}',
        component: () => import('@/views/${projectName}/${table.entityName?uncap_first}/view'),
        meta: {title: '查看${table.tableComment}', icon: '${table.entityName?uncap_first}-view'},
        hidden: true
      },
      {
        path: 'update${table.entityName?cap_first}',
        name: 'update${table.entityName?cap_first}',
        component: () => import('@/views/${projectName}/${table.entityName?uncap_first}/update'),
        meta: {title: '修改${table.tableComment}', icon: '${table.entityName?uncap_first}-add'},
        hidden: true
      }
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})

