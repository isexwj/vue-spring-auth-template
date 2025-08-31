<template>
  <div class="home-container">
    <!-- 独立的顶部导航栏 -->
    <header class="home-header">
      <div class="header-content">
        <div class="logo-section">
          <h1 class="logo">Vue Auth System</h1>
        </div>
        
        <div class="user-section">
          <el-dropdown @command="handleCommand">
            <div class="user-info">
              <el-avatar :size="32" class="user-avatar">
                {{ userStore.username.charAt(0).toUpperCase() }}
              </el-avatar>
              <span class="username">{{ userStore.username }}</span>
              <el-icon size="14"><ArrowDown /></el-icon>
            </div>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="profile">
                  <el-icon size="14"><User /></el-icon>
                  个人资料
                </el-dropdown-item>
                <el-dropdown-item command="settings">
                  <el-icon size="14"><Setting /></el-icon>
                  设置
                </el-dropdown-item>
                <el-dropdown-item divided command="logout">
                  <el-icon size="14"><SwitchButton /></el-icon>
                  退出登录
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>
    </header>

    <!-- 主要内容区域 -->
    <main class="home-main">
      <div class="welcome-section">
        <div class="welcome-content">
          <h1 class="welcome-title">欢迎回来，{{ userStore.username }}！</h1>
          <p class="welcome-subtitle">您已成功登录系统</p>
          
          <div class="user-info-card">
            <div class="user-avatar">
              <el-icon size="32"><User /></el-icon>
            </div>
            <div class="user-details">
              <h3>用户信息</h3>
              <p><strong>用户名：</strong>{{ userStore.username }}</p>
              <p><strong>登录状态：</strong><span class="status-success">已登录</span></p>
              <p><strong>登录时间：</strong>{{ loginTime }}</p>
            </div>
          </div>
          
          <div class="action-buttons">
            <el-button type="primary" size="default" @click="refreshInfo">
              <el-icon size="16"><Refresh /></el-icon>
              刷新信息
            </el-button>
            <el-button type="danger" size="default" @click="handleLogout">
              <el-icon size="16"><SwitchButton /></el-icon>
              退出登录
            </el-button>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/user'
import { ElMessage, ElMessageBox } from 'element-plus'
import { User, Refresh, SwitchButton, ArrowDown, Setting } from '@element-plus/icons-vue'

const router = useRouter()
const userStore = useUserStore()
const loginTime = ref('')

onMounted(() => {
  loginTime.value = new Date().toLocaleString('zh-CN')
})

const refreshInfo = () => {
  loginTime.value = new Date().toLocaleString('zh-CN')
  ElMessage.success('信息已刷新')
}

const handleCommand = (command) => {
  switch (command) {
    case 'profile':
      ElMessage.info('个人资料功能开发中...')
      break
    case 'settings':
      ElMessage.info('设置功能开发中...')
      break
    case 'logout':
      handleLogout()
      break
  }
}

const handleLogout = async () => {
  try {
    await ElMessageBox.confirm(
      '确定要退出登录吗？',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
    )
    
    userStore.clearUser()
    ElMessage.success('已退出登录')
    router.push('/login')
  } catch {
    // 用户取消操作
  }
}
</script>

<style scoped>
.home-container {
  min-height: 100vh;
  background: #f8fafc;
}

.home-header {
  background: white;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
  padding: 0;
  position: sticky;
  top: 0;
  z-index: 1000;
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 64px;
  padding: 0 24px;
}

.logo-section .logo {
  color: #1e293b;
  font-size: 24px;
  font-weight: 600;
  margin: 0;
}

.user-section {
  display: flex;
  align-items: center;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  padding: 8px 12px;
  border-radius: 8px;
  transition: background-color 0.2s;
}

.user-info:hover {
  background-color: #f1f5f9;
}

.user-avatar {
  background: #3b82f6;
  color: white;
  font-weight: 600;
  font-size: 14px;
}

.username {
  color: #1e293b;
  font-weight: 500;
  font-size: 14px;
}

.home-main {
  flex: 1;
}

.welcome-section {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: calc(100vh - 64px);
  padding: 40px 20px;
}

.welcome-content {
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
  padding: 40px;
  text-align: center;
  max-width: 600px;
  width: 100%;
}

.welcome-title {
  color: #1e293b;
  font-size: 28px;
  font-weight: 600;
  margin: 0 0 12px 0;
}

.welcome-subtitle {
  color: #64748b;
  font-size: 16px;
  margin: 0 0 32px 0;
}

.user-info-card {
  background: #f8fafc;
  border: 1px solid #e2e8f0;
  border-radius: 12px;
  padding: 24px;
  margin-bottom: 32px;
  display: flex;
  align-items: center;
  gap: 20px;
}

.user-avatar {
  background: #3b82f6;
  color: white;
  border-radius: 50%;
  width: 64px;
  height: 64px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.user-details {
  text-align: left;
  flex: 1;
}

.user-details h3 {
  color: #1e293b;
  font-size: 18px;
  font-weight: 600;
  margin: 0 0 16px 0;
}

.user-details p {
  color: #475569;
  margin: 8px 0;
  font-size: 14px;
}

.status-success {
  color: #10b981;
  font-weight: 600;
}

.action-buttons {
  display: flex;
  gap: 12px;
  justify-content: center;
  flex-wrap: wrap;
}

.action-buttons .el-button {
  min-width: 120px;
  height: 40px;
  font-size: 14px;
  border-radius: 8px;
}

@media (max-width: 768px) {
  .header-content {
    padding: 0 16px;
  }
  
  .welcome-content {
    padding: 30px 20px;
  }
  
  .welcome-title {
    font-size: 24px;
  }
  
  .user-info-card {
    flex-direction: column;
    text-align: center;
    gap: 16px;
  }
  
  .user-details {
    text-align: center;
  }
  
  .action-buttons {
    flex-direction: column;
    align-items: center;
  }
  
  .action-buttons .el-button {
    width: 100%;
    max-width: 280px;
  }
}
</style>
