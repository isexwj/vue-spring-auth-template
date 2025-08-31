<template>
  <div>
    <el-menu mode="horizontal">
      <el-menu-item index="1" @click="$router.push('/')">首页</el-menu-item>
      <el-menu-item index="2" v-if="!userStore.token" @click="$router.push('/login')">登录</el-menu-item>
      <el-sub-menu v-else index="3">
        <template #title>欢迎，{{ userStore.username }}</template>
        <el-menu-item @click="logout">退出登录</el-menu-item>
      </el-sub-menu>
    </el-menu>
    <router-view />
  </div>
</template>

<script setup>
import { useUserStore } from '@/store/user'
import { useRouter } from 'vue-router'

const router = useRouter()

const userStore = useUserStore()

const logout = () => {
  userStore.clearUser()
  router.push('/login')
}
</script>