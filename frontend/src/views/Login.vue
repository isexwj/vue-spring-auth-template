<template>
  <el-card class="w-96 mx-auto mt-20 p-6 shadow-lg">
    <h2 class="text-xl font-bold mb-4">用户登录</h2>
    <el-form :model="form" @submit.prevent="handleLogin">
      <el-form-item label="用户名">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-button type="primary" @click="handleLogin" class="w-full">登录</el-button>
    </el-form>
  </el-card>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/user'
import { login } from '@/api/user'

const router = useRouter()
const userStore = useUserStore()

const form = reactive({
  username: '',
  password: ''
})

const handleLogin = async () => {
  try {
    // 使用导入的API函数而不是直接调用request
    const res = await login(form)
    
    // 检查响应状态
    if (res.code === 200 && res.data) {
      userStore.setUser(res.data.token, res.data.username)
      router.push('/home')
    } else {
      console.error('登录失败:', res.message)
    }
  } catch (e) {
    console.error('登录错误:', e)
  }
}
</script>


<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.box-card {
  width: 400px;
  padding: 20px;
}
</style>
