import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from scipy import stats
import statsmodels.api as sm

# Prueba NumPy
data = np.random.normal(size=1000)
print(f"Media de los datos: {np.mean(data)}")

# Prueba Pandas
df = pd.DataFrame({
    'col1': np.random.randn(100),
    'col2': np.random.randn(100)
})
print(df.head())

# Prueba Matplotlib
plt.hist(data, bins=30)
plt.title("Histograma de datos")
plt.draw()

# Prueba Seaborn
sns.boxplot(data=df)
plt.title("Boxplot con Seaborn")
plt.draw()

# Prueba SciPy
stat, p = stats.shapiro(data)
print(f"Prueba de normalidad de Shapiro-Wilk: estadístico={stat}, p-valor={p}")

# Prueba Statsmodels
X = np.random.rand(100)
y = 2 * X + np.random.randn(100) * 0.1
X = sm.add_constant(X)  # Agregar constante para el modelo
model = sm.OLS(y, X)
results = model.fit()
print(results.summary())